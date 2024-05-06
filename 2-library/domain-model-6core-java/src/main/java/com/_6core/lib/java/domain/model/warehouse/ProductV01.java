package com._6core.lib.java.domain.model.warehouse;

import java.io.Serializable;
import java.math.BigInteger;

public interface ProductV01 extends Serializable {

    /**
     * The unique identifier of product.
     *
     * @return the product id
     */
    String getProductId();

    /**
     * The product name to be shown on the title.
     *
     * @return the product name
     */
    String getName();

    /**
     * The product description to be shown on the description.
     *
     * @return the product description
     */
    String getDescription();

    /**
     * The image location path.
     *
     * @return the image metadata
     */
    String getImage();

    /**
     * The price of unit of product.
     * <p>
     * The value returned represents the price of the unit where the last two digits
     * correspond to the fractional unit of the currency, typically representing cents or a
     * similar subunit.
     *
     * @return the price
     */
    BigInteger getPrice();

    /**
     * The category of product, e.g. books, computers, and so on.
     * <p>
     * Typically needed for filtering.
     *
     * @return the category
     */
    String getCategory();
}
