package com._6core.platform.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequest {
    private String productId;
    private Integer quantity;
    private LocalDateTime reservedTo;
}
