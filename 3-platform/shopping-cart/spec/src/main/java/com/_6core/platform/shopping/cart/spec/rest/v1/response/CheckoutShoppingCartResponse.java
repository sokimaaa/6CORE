package com._6core.platform.shopping.cart.spec.rest.v1.response;

import java.io.Serializable;

public record CheckoutShoppingCartResponse(
    String cartId, String transactionId, String orderId, Boolean ok) implements Serializable {}
