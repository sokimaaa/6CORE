package com._6core.platform.shopping.cart.domain;

import java.util.List;

public record CreateOrderRequest(List<String> productIds) {}
