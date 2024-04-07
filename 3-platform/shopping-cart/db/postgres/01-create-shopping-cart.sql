-- DROPPING TABLE IF EXISTS
DROP TABLE IF EXISTS shopping_cart;

-- CREATING TABLE "Shopping cart"
CREATE TABLE shopping_cart (
                               cart_id VARCHAR(255) NOT NULL,
                               product_ids TEXT[],
                               PRIMARY KEY (cart_id)
);

-- SET COMMENT ON TABLE
COMMENT ON TABLE shopping_cart IS 'Table containing shopping cart information';

-- SET COMMENT ON COLUMN
COMMENT ON COLUMN shopping_cart.cart_id IS 'The cart unique identifier';
COMMENT ON COLUMN shopping_cart.product_ids IS 'A list of product ids that have been added to the shopping cart';