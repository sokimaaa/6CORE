package com._6core.platform.domain.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ReservationResponse {
  private String productId;
  private Integer quantity;
  private LocalDateTime reservedTo;
}
