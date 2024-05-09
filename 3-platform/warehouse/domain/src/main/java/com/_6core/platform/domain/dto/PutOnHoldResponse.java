package com._6core.platform.domain.dto;

import lombok.Data;

@Data
public class PutOnHoldResponse {
    private String productId;
    private Integer quantity;
    private Boolean result;
}
