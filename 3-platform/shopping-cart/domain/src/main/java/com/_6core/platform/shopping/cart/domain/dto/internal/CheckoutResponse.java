package com._6core.platform.shopping.cart.domain.dto.internal;

public record CheckoutResponse(String cartId, String transactionId, String orderId, Boolean ok) {}
