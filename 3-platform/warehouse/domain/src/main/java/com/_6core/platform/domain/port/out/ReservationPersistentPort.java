package com._6core.platform.domain.port.out;

import com._6core.lib.hexagonal.annotations.PortOut;
import com._6core.platform.domain.dto.ReservationRequest;
import com._6core.platform.domain.dto.ReservationResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@PortOut
public interface ReservationPersistentPort {
    Mono<ReservationResponse> save(ReservationRequest request);

    Flux<ReservationResponse> getReservationsForRelease();
}

