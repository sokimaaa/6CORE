package com._6core.platform.domain.usecase;

import com._6core.lib.hexagonal.annotations.UseCase;
import com._6core.platform.domain.dto.PutOnHoldRequest;
import com._6core.platform.domain.dto.PutOnHoldResponse;
import reactor.core.publisher.Mono;

@UseCase
public interface PutOnHoldUseCase {
  Mono<PutOnHoldResponse> reserve(PutOnHoldRequest putOnHoldRequest);

  Mono<Void> releaseReservations();
}
