package com._6core.platform.shopping.cart.spec.rest.v1.response;

import java.io.Serializable;
import java.util.List;

public record CleanShoppingCartResponse(String cartId, List<String> cleanedProductIds, Boolean ok)
    implements Serializable {}
