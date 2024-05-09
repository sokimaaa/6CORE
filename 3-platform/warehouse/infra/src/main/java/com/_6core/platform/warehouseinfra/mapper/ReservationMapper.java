package com._6core.platform.warehouseinfra.mapper;

import com._6core.platform.domain.dto.ReservationRequest;
import com._6core.platform.domain.dto.ReservationResponse;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ReservationRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReservationMapper {
  ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

  ReservationRecord toRecord(ReservationRequest request);

  ReservationResponse toResponse(ReservationRecord record);
}
