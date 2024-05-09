package com._6core.platform.warehouse.app.usecase;

import com._6core.lib.java.domain.model.warehouse.InventoryV01;
import com._6core.lib.java.domain.model.warehouse.immutable.ImmutableInventoryV01Impl;
import com._6core.lib.java.domain.model.warehouse.immutable.ImmutableProductV01Impl;
import com._6core.lib.java.domain.model.warehouse.immutable.ImmutableWarehouseV01Impl;
import com._6core.lib.java.domain.model.warehouse.immutable.InventoryV01Impl;
import com._6core.platform.domain.dto.PutOnHoldRequest;
import com._6core.platform.domain.dto.PutOnHoldResponse;
import com._6core.platform.domain.dto.ReservationRequest;
import com._6core.platform.domain.dto.ReservationResponse;
import com._6core.platform.domain.port.out.InventoryPersistentPort;
import com._6core.platform.domain.port.out.ReservationPersistentPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static com._6core.platform.warehouse.app.usecase.PutOnHoldService.RESERVATION_TIME_SECONDS;
import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
class PutOnHoldServiceTest {
    @Mock
    private InventoryPersistentPort inventoryPersistentPort;
    @Mock
    private ReservationPersistentPort reservationPersistentPort;
    @InjectMocks
    private PutOnHoldService putOnHoldService;

    @Test
    void reserve_validPutOnHoldRequest_successPutOnHoldResponse() {
        //Given
        PutOnHoldRequest request = new PutOnHoldRequest("2", 4);

        InventoryV01Impl inventoryWithEnoughQuantity = getInventoryWithEnoughQuantity(request);
        ReservationRequest reservationRequest = getReservationRequestByPutOnHoldRequest(request);
        ReservationResponse reservationResponse = getReservationResponseByRequest(reservationRequest);
        InventoryV01Impl inventoryAfterSubstr = getInventoryAfterSubstr(inventoryWithEnoughQuantity, request.quantity());
        PutOnHoldResponse expected = getSuccessPutOnHoldResponseByRequest(request);


        Mockito.when(inventoryPersistentPort.findByProductId(request.productId()))
                .thenReturn(Mono.just(inventoryWithEnoughQuantity));
        Mockito.when(reservationPersistentPort.save(argThat(arg ->
                arg.getReservedTo().isAfter(reservationRequest.getReservedTo().minusSeconds(1))
                        && arg.getReservedTo().isBefore(reservationRequest.getReservedTo().plusSeconds(1))
                        && arg.getProductId().equals(reservationRequest.getProductId())
                        && arg.getQuantity().equals(reservationRequest.getQuantity())
        ))).thenReturn(Mono.just(reservationResponse));
        Mockito.when(inventoryPersistentPort.update(inventoryAfterSubstr))
                .thenReturn(Mono.just(inventoryAfterSubstr));

        //When
        putOnHoldService.reserve(request)
                .log()
                .as(StepVerifier::create)
                .consumeNextWith(putOnHoldResponse -> {
                    Assertions.assertEquals(putOnHoldResponse.getQuantity(), request.quantity());
                    Assertions.assertEquals(putOnHoldResponse.getProductId(), request.productId());
                    Assertions.assertTrue(putOnHoldResponse.getResult());
                })
                .verifyComplete();

    }

    @Test
    void reserve_validPutOnHoldRequest_failedPutOnHoldResponse() {
        //Given
        PutOnHoldRequest request = new PutOnHoldRequest("2", 4);

        InventoryV01Impl inventoryWithEnoughQuantity = getInventoryWithNotEnoughQuantity(request);
        PutOnHoldResponse expected = getFailedPutOnHoldResponseByRequest(request);


        Mockito.when(inventoryPersistentPort.findByProductId(request.productId()))
                .thenReturn(Mono.just(inventoryWithEnoughQuantity));

        //When
        putOnHoldService.reserve(request)
                .log()
                .as(StepVerifier::create)
                .consumeNextWith(putOnHoldResponse -> {
                    Assertions.assertEquals(expected.getQuantity(), request.quantity());
                    Assertions.assertEquals(expected.getProductId(), request.productId());
                    Assertions.assertFalse(expected.getResult());
                })
                .verifyComplete();

    }

    @Test
    void releaseReservations_success() {
        //Given
        ReservationResponse reservation1 = new ReservationResponse();
        reservation1.setProductId("1");
        reservation1.setReservedTo(LocalDateTime.now().minusHours(1));
        reservation1.setQuantity(1);
        ReservationResponse reservation2 = new ReservationResponse();
        reservation2.setProductId("2");
        reservation2.setReservedTo(LocalDateTime.now().minusHours(1));
        reservation2.setQuantity(2);

        ImmutableInventoryV01Impl inventoryBeforeProcessing1 = getInventoryByReservationResponse(reservation1);
        ImmutableInventoryV01Impl inventoryBeforeProcessing2 = getInventoryByReservationResponse(reservation2);

        ImmutableInventoryV01Impl inventoryAfterProcessing1 =
                getInventoryWithReleasedQuantity(inventoryBeforeProcessing1, reservation1);
        ImmutableInventoryV01Impl inventoryAfterProcessing2 =
                getInventoryWithReleasedQuantity(inventoryBeforeProcessing2, reservation2);

        Mockito.when(reservationPersistentPort.getReservationsForRelease())
                .thenReturn(Flux.just(reservation1, reservation2));

        Mockito.when(inventoryPersistentPort.findByProductId(reservation1.getProductId()))
                .thenReturn(Mono.just(inventoryBeforeProcessing1));
        Mockito.when(inventoryPersistentPort.findByProductId(reservation2.getProductId()))
                .thenReturn(Mono.just(inventoryBeforeProcessing2));

        Mockito.when(inventoryPersistentPort.update(inventoryAfterProcessing1))
                .thenReturn(Mono.just(inventoryAfterProcessing1));
        Mockito.when(inventoryPersistentPort.update(inventoryAfterProcessing2))
                .thenReturn(Mono.just(inventoryAfterProcessing2));

        //When

        putOnHoldService.releaseReservations()
                .as(StepVerifier::create)
                .verifyComplete();
    }

    private ImmutableInventoryV01Impl getInventoryWithReleasedQuantity(
            ImmutableInventoryV01Impl inventory,
            ReservationResponse reservation) {
        return inventory
                .withAvailableQuantity(inventory.getAvailableQuantity() + reservation.getQuantity());
    }

    private ImmutableInventoryV01Impl getInventoryByReservationResponse(ReservationResponse response) {
        ImmutableProductV01Impl productV01 = getDefaultImmutableProductV01()
                .withProductId(response.getProductId());
        return getDefaultImmutableInventoryV01()
                .withProduct(productV01);
    }

    private ImmutableInventoryV01Impl getInventoryWithEnoughQuantity(PutOnHoldRequest request) {
        ImmutableProductV01Impl productV01 = getDefaultImmutableProductV01()
                .withProductId(request.productId());
        ImmutableWarehouseV01Impl warehouseV01 = getDefaultImmutableWarehouseV01();
        return getDefaultImmutableInventoryV01()
                .withProduct(productV01)
                .withWarehouse(warehouseV01)
                .withActualQuantity(request.quantity() + 2)
                .withAvailableQuantity(request.quantity() + 1);
    }

    private ImmutableProductV01Impl getDefaultImmutableProductV01() {
        return ImmutableProductV01Impl.builder()
                .productId("1")
                .category("category")
                .description("description")
                .image("image.png")
                .price(BigInteger.TWO)
                .name("name")
                .build();
    }

    private ImmutableWarehouseV01Impl getDefaultImmutableWarehouseV01() {
        return ImmutableWarehouseV01Impl.builder().warehouseId("9").address("address").build();
    }

    private ImmutableInventoryV01Impl getDefaultImmutableInventoryV01() {
        return ImmutableInventoryV01Impl.builder()
                .inventoryId("3")
                .product(getDefaultImmutableProductV01())
                .warehouse(getDefaultImmutableWarehouseV01())
                .actualQuantity(3)
                .availableQuantity(3)
                .build();
    }

    private ImmutableInventoryV01Impl getInventoryWithNotEnoughQuantity(PutOnHoldRequest request) {
        ImmutableProductV01Impl productV01 = getDefaultImmutableProductV01()
                .withProductId(request.productId());
        ImmutableWarehouseV01Impl warehouseV01 = getDefaultImmutableWarehouseV01();
        return getDefaultImmutableInventoryV01()
                .withProduct(productV01)
                .withWarehouse(warehouseV01)
                .withActualQuantity(request.quantity() - 1)
                .withAvailableQuantity(request.quantity() - 2);
    }

    private InventoryV01Impl getInventoryAfterSubstr(InventoryV01 inventoryAfterSubstr, Integer quantity) {
        return ImmutableInventoryV01Impl.builder()
                .inventoryId(inventoryAfterSubstr.getInventoryId())
                .product(inventoryAfterSubstr.getProduct())
                .warehouse(inventoryAfterSubstr.getWarehouse())
                .actualQuantity(inventoryAfterSubstr.getActualQuantity())
                .availableQuantity(inventoryAfterSubstr.getAvailableQuantity() - quantity)
                .build();
    }

    private ReservationRequest getReservationRequestByPutOnHoldRequest(PutOnHoldRequest request) {
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setProductId(request.productId());
        reservationRequest.setQuantity(request.quantity());
        reservationRequest.setReservedTo(LocalDateTime.now().plusSeconds(RESERVATION_TIME_SECONDS));
        return reservationRequest;
    }

    private PutOnHoldResponse getSuccessPutOnHoldResponseByRequest(PutOnHoldRequest request) {
        PutOnHoldResponse response = getPutOnHoldResponseByRequestWithoutResult(request);
        response.setResult(true);
        return response;
    }

    private PutOnHoldResponse getFailedPutOnHoldResponseByRequest(PutOnHoldRequest request) {
        PutOnHoldResponse response = getPutOnHoldResponseByRequestWithoutResult(request);
        response.setResult(false);
        return response;
    }

    private PutOnHoldResponse getPutOnHoldResponseByRequestWithoutResult(PutOnHoldRequest request) {
        PutOnHoldResponse response = new PutOnHoldResponse();
        response.setProductId(request.productId());
        response.setQuantity(request.quantity());
        return response;
    }

    private ReservationResponse getReservationResponseByRequest(ReservationRequest request) {
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setProductId(request.getProductId());
        reservationResponse.setQuantity(request.getQuantity());
        reservationResponse.setReservedTo(request.getReservedTo());
        return reservationResponse;
    }
}