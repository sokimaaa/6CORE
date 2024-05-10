package com._6core.platform.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Valid
public record PutOnHoldRequest(@NotBlank String productId, @Min(1) Integer quantity) {}
