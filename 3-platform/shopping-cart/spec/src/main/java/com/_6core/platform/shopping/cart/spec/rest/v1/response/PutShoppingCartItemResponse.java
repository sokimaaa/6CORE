package com._6core.platform.shopping.cart.spec.rest.v1.response;

import java.io.Serializable;

public record PutShoppingCartItemResponse(String cartId, String productId, Boolean ok)
    implements Serializable {}
