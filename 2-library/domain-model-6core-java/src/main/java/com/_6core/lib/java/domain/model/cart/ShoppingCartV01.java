package com._6core.lib.java.domain.model.cart;

import java.io.Serializable;

public interface ShoppingCartV01 extends Serializable {
    /**
     * The cart unique identifier.
     *
     * @return the cart id
     */
    String getCartId();

    /**
     * A list of product ids that have been added to the shopping cart.
     * <p>
     * If a product is added multiple times, its id will appear in the list
     * multiple times, corresponding to the quantity added. This allows for tracking both the
     * presence and quantity of products within the cart through the repetition of ids.
     *
     * @return the product ids
     */
    List<String> getProductIds();
}
