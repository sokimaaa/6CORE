package com._6core.platform.orderinfra.dto;

import java.util.List;

public record CreateOrderRequest(List<String> productIds) {}
