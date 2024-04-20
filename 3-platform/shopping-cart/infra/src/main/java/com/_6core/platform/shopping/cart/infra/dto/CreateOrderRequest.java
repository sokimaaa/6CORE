package com._6core.platform.shopping.cart.infra.dto;

import java.util.List;

public record CreateOrderRequest(List<String> product_ids) {}
