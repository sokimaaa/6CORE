package com._6core.platform.warehouseinfra.adapter.persistent;

import com._6core.platform.domain.dto.ReservationRequest;
import com._6core.platform.domain.dto.ReservationResponse;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ReservationRecord;
import com._6core.platform.warehouseinfra.mapper.ReservationMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ReservationPersistentTest {
  @Mock private ReservationMapper reservationMapper;
  @InjectMocks private ReservationPersistent reservationPersistent;

  @Test
  void save_validReservationRequest_validReservationResponse() {

    // Given
    ReservationRequest request = new ReservationRequest();
    request.setProductId("4");
    request.setQuantity(3);
    request.setReservedTo(LocalDateTime.now());

    ReservationRecord record = getRecordByReservationRequest(request);

    ReservationResponse reservationResponse = getResponseByRecord(record);

    Mockito.when(reservationMapper.toRecord(request)).thenReturn(record);
    Mockito.when(reservationMapper.toResponse(record)).thenReturn(reservationResponse);

    // When
    reservationPersistent
        .save(request)
        .as(StepVerifier::create)
        .consumeNextWith(
            response -> {
              Assertions.assertEquals(reservationResponse.getQuantity(), response.getQuantity());
              Assertions.assertEquals(reservationResponse.getProductId(), response.getProductId());
              Assertions.assertEquals(
                  reservationResponse.getReservedTo(), response.getReservedTo());
            })
        .verifyComplete();
  }

  @Test
  void getReservationsForRelease() throws Exception {
    // Given
    ReservationRequest requestWithPastDate = new ReservationRequest();
    requestWithPastDate.setProductId("1");
    requestWithPastDate.setQuantity(3);
    requestWithPastDate.setReservedTo(LocalDateTime.now().minusHours(1));

    ReservationRequest requestWithFutureDate = new ReservationRequest();
    requestWithFutureDate.setProductId("1");
    requestWithFutureDate.setQuantity(3);
    requestWithFutureDate.setReservedTo(LocalDateTime.now().plusHours(2));

    ReservationRecord recordWithPastDate = getRecordByReservationRequest(requestWithPastDate);

    ReservationResponse reservationResponseWithPastDate = getResponseByRecord(recordWithPastDate);

    Mockito.when(reservationMapper.toResponse(recordWithPastDate))
        .thenReturn(reservationResponseWithPastDate);

    reservationPersistent
        .save(requestWithPastDate)
        .zipWith(reservationPersistent.save(requestWithFutureDate))
        .block();

    // When

    reservationPersistent
        .getReservationsForRelease()
        .as(StepVerifier::create)
        .consumeNextWith(
            putOnHoldResponse -> {
              Assertions.assertEquals(
                  reservationResponseWithPastDate.getQuantity(), putOnHoldResponse.getQuantity());
              Assertions.assertEquals(
                  reservationResponseWithPastDate.getProductId(), putOnHoldResponse.getProductId());
              Assertions.assertEquals(
                  reservationResponseWithPastDate.getReservedTo(),
                  putOnHoldResponse.getReservedTo());
            })
        .expectNextCount(1)
        .verifyComplete();
  }

  private ReservationRecord getRecordByReservationRequest(ReservationRequest request) {
    return new ReservationRecord(
        request.getProductId(), request.getQuantity(), request.getReservedTo());
  }

  private ReservationResponse getResponseByRecord(ReservationRecord record) {
    ReservationResponse response = new ReservationResponse();
    response.setProductId(record.productId());
    response.setQuantity(record.quantity());
    response.setReservedTo(record.reservedTo());
    return response;
  }
}
