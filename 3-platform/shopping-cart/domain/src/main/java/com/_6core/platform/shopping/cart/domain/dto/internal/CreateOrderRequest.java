package com._6core.platform.shopping.cart.domain.dto.internal;

import java.util.List;

public record CreateOrderRequest(List<String> productIds) {}
