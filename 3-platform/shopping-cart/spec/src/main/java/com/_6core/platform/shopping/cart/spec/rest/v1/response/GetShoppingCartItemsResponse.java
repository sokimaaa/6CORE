package com._6core.platform.shopping.cart.spec.rest.v1.response;

import java.io.Serializable;
import java.util.List;

public record GetShoppingCartItemsResponse(String cartId, List<String> products, Boolean ok)
    implements Serializable {}
