package com._6core.platform.warehouseinfra.adapter.persistent;

import com._6core.platform.domain.dto.ReservationRequest;
import com._6core.platform.domain.dto.ReservationResponse;
import com._6core.platform.domain.port.out.ReservationPersistentPort;
import com._6core.platform.warehouseinfra.adapter.driven.persistence.entity.ReservationRecord;
import com._6core.platform.warehouseinfra.mapper.ReservationMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ReservationPersistent implements ReservationPersistentPort {

  private static final List<ReservationRecord> reservationDB = new ArrayList<>();
  private final ReservationMapper reservationMapper;

  @Override
  public Mono<ReservationResponse> save(ReservationRequest request) {
    ReservationRecord record = reservationMapper.toRecord(request);
    reservationDB.add(record);
    ReservationResponse reservationResponse = reservationMapper.toResponse(record);
    return Mono.just(reservationResponse);
  }

  @Override
  public Flux<ReservationResponse> getReservationsForRelease() {
    LocalDateTime now = LocalDateTime.now();
    List<ReservationRecord> listToRemove =
        reservationDB.stream()
            .filter(reservation -> reservation.reservedTo().isBefore(now))
            .toList();
    reservationDB.removeAll(listToRemove);

    return Flux.fromStream(listToRemove.stream().map(reservationMapper::toResponse));
  }
}
