package com._6core.platform.warehouse.app.usecase;

import com._6core.lib.hexagonal.annotations.UseCase;
import com._6core.lib.java.domain.model.warehouse.InventoryV01;
import com._6core.lib.java.domain.model.warehouse.immutable.ImmutableInventoryV01Impl;
import com._6core.lib.java.domain.model.warehouse.immutable.InventoryV01Impl;
import com._6core.platform.domain.dto.ReservationRequest;
import com._6core.platform.domain.port.out.ReservationPersistentPort;
import com._6core.platform.domain.port.out.InventoryPersistentPort;
import com._6core.platform.domain.usecase.PutOnHoldUseCase;
import com._6core.platform.domain.dto.PutOnHoldRequest;
import com._6core.platform.domain.dto.PutOnHoldResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@UseCase
public class PutOnHoldService implements PutOnHoldUseCase {
    public static final Long RESERVATION_TIME_SECONDS = 1800L;
    private final InventoryPersistentPort inventoryPersistentPort;
    private final ReservationPersistentPort reservationPersistentPort;

    public Mono<PutOnHoldResponse> reserve(PutOnHoldRequest putOnHoldRequest) {
        String productId = putOnHoldRequest.productId();
        Integer neededQuantity = putOnHoldRequest.quantity();

        return inventoryPersistentPort.findByProductId(productId)
                .filter(inventory -> inventory.getAvailableQuantity() >= neededQuantity)
                .map(inventory -> getInventoryWithSubstrQuantity(inventory, neededQuantity))
                .flatMap(inventory -> {
                    ReservationRequest reservationRequest = getReservationRequest(inventory, neededQuantity);
                    reservationPersistentPort.save(reservationRequest);
                    return inventoryPersistentPort.update(inventory);
                })
                .map(inventory ->
                        getSuccessPunOnHoldResponse(inventory, neededQuantity)
                )
                .defaultIfEmpty(getFailedPunOnHoldResponse(putOnHoldRequest));
    }


    public Mono<Void> releaseReservations() {
        return reservationPersistentPort.getReservationsForRelease()
                .flatMap(reservationResponse ->
                        inventoryPersistentPort.findByProductId(reservationResponse.getProductId())
                                .map(inventoryFromDb -> getInventoryWithPlusQuantity(inventoryFromDb, reservationResponse.getQuantity())
                                ))
                .flatMap(inventoryPersistentPort::update)
                .then();
    }

    private ReservationRequest getReservationRequest(InventoryV01 inventory, Integer neededQuantity) {
        ReservationRequest request = new ReservationRequest();
        request.setProductId(inventory.getProduct().getProductId());
        request.setQuantity(neededQuantity);
        request.setReservedTo(LocalDateTime.now().plusSeconds(RESERVATION_TIME_SECONDS));
        return request;
    }

    private InventoryV01Impl getInventoryWithSubstrQuantity(InventoryV01Impl inventory, Integer neededQuantity) {
        return ImmutableInventoryV01Impl.builder()
                .actualQuantity(inventory.getActualQuantity())
                .availableQuantity(inventory.getAvailableQuantity() - neededQuantity)
                .inventoryId(inventory.getInventoryId())
                .product(inventory.getProduct())
                .warehouse(inventory.getWarehouse())
                .build();
    }

    private PutOnHoldResponse getSuccessPunOnHoldResponse(InventoryV01Impl inventory, Integer neededQuantity) {
        PutOnHoldResponse response = new PutOnHoldResponse();
        response.setProductId(inventory.getProduct().getProductId());
        response.setQuantity(neededQuantity);
        response.setResult(true);
        return response;
    }

    private PutOnHoldResponse getFailedPunOnHoldResponse(PutOnHoldRequest putOnHoldRequest) {
        PutOnHoldResponse response = new PutOnHoldResponse();
        response.setProductId(putOnHoldRequest.productId());
        response.setQuantity(putOnHoldRequest.quantity());
        response.setResult(false);
        return response;
    }


    private InventoryV01Impl getInventoryWithPlusQuantity(InventoryV01Impl inventory, Integer quantityForRelease) {
        return ImmutableInventoryV01Impl.builder()
                .actualQuantity(inventory.getActualQuantity())
                .availableQuantity(inventory.getAvailableQuantity() + quantityForRelease)
                .inventoryId(inventory.getInventoryId())
                .product(inventory.getProduct())
                .warehouse(inventory.getWarehouse())
                .build();
    }
}
