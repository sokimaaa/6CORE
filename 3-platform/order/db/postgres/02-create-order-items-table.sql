-- order_items table

CREATE TABLE IF NOT EXISTS public.order_items (
    id          varchar NOT NULL,
    order_id    varchar NOT NULL,
    product_id  varchar NOT NULL,
    quantity    int     NOT NULL,
    price       bigint  NOT NULL,
    CONSTRAINT order_items_pk PRIMARY KEY (id),
    CONSTRAINT order_items_orders_fk FOREIGN KEY (id) REFERENCES public.orders (id)
);

-- Column checks

ALTER TABLE public.order_items
    ADD CONSTRAINT order_items_quantity_check CHECK (quantity > 0);
ALTER TABLE public.order_items
    ADD CONSTRAINT order_items_price_check CHECK (price >= 0);

-- Column comments

COMMENT
    ON COLUMN public.order_items.id IS 'PK of order item record';
COMMENT
    ON COLUMN public.order_items.order_id IS 'FK on order record';
COMMENT
    ON COLUMN public.order_items.product_id IS 'FK on product record';
COMMENT
    ON COLUMN public.order_items.quantity IS 'quantity of products that was ordered';
COMMENT
    ON COLUMN public.order_items.price IS 'computed price of the current order item';
