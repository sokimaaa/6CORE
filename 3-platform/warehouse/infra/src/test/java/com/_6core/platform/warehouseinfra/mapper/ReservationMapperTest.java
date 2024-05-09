package com._6core.platform.warehouseinfra.mapper;

import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ReservationRecord;
import com._6core.platform.domain.dto.ReservationRequest;
import com._6core.platform.domain.dto.ReservationResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ReservationMapperTest {

    @Test
    void toRecord_validReservationRequest_validRecord() {
        //Given
        ReservationRequest request = new ReservationRequest();
        request.setProductId("123");
        request.setQuantity(9);
        request.setReservedTo(LocalDateTime.now());
        ReservationRecord expected = new ReservationRecord(
                request.getProductId(),
                request.getQuantity(),
                request.getReservedTo()
        );

        //When
        ReservationRecord actual = ReservationMapper.INSTANCE.toRecord(request);

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void toResponse_validRecord_validReservationResponse() {
        //Given
        ReservationRecord record = new ReservationRecord("321",10, LocalDateTime.now());
        ReservationResponse expected = new ReservationResponse();
        expected.setQuantity(record.quantity());
        expected.setProductId(record.productId());
        expected.setReservedTo(record.reservedTo());

        //When
        ReservationResponse actual = ReservationMapper.INSTANCE.toResponse(record);

        //Then
        assertThat(actual).isEqualTo(expected);
    }
}