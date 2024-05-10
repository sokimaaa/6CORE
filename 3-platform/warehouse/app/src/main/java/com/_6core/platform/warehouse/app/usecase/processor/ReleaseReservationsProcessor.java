package com._6core.platform.warehouse.app.usecase.processor;

import com._6core.platform.warehouse.app.usecase.PutOnHoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class ReleaseReservationsProcessor {
  private final PutOnHoldService putOnHoldService;

  @Scheduled(cron = "0 * * * * *")
  void execute() {
    putOnHoldService.releaseReservations();
  }
}
