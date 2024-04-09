-- CREATING TABLE "shopping_carts"
CREATE TABLE IF NOT EXISTS shopping_carts
(
    id         VARCHAR(20) NOT NULL,
    product_id VARCHAR(150) NOT NULL
);

-- SET INDEXES WHERE NEEDED
CREATE INDEX idx_shopping_carts_id ON shopping_carts (id);

-- SET COMMENT ON TABLE
COMMENT ON TABLE shopping_carts IS 'Table containing shopping cart information';

-- SET COMMENT ON COLUMN
COMMENT ON COLUMN shopping_carts.id IS 'The cart identifier';
COMMENT ON COLUMN shopping_carts.product_id IS 'A list of product ids that have been added to the shopping cart';
