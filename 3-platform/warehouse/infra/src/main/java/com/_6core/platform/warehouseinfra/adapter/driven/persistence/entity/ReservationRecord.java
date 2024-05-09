package com._6core.platform.warehouseinfra.adapter.driven.persistence.entity;

import java.time.LocalDateTime;

public record ReservationRecord(String productId, Integer quantity, LocalDateTime reservedTo) {}
