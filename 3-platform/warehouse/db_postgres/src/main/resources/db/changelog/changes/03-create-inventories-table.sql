-- inventories table

CREATE TABLE public.inventories
(
    id                 varchar NOT NULL,
    warehouse_id       varchar NOT NULL,
    product_id         varchar NULL,
    actual_quantity    int     NULL,
    available_quantity int     NULL,
    CONSTRAINT inventories_pk PRIMARY KEY (id),
    CONSTRAINT inventories_actual_quantity_check CHECK (actual_quantity >= 0),
    CONSTRAINT inventories_available_quantity_check CHECK (available_quantity >= 0),
    CONSTRAINT inventories_check CHECK (available_quantity <= actual_quantity),
    CONSTRAINT inventories_warehouses_fk FOREIGN KEY (id) REFERENCES public.warehouses (id),
    CONSTRAINT inventories_products_fk FOREIGN KEY (id) REFERENCES public.products (id)
);

-- Column comments

COMMENT ON COLUMN public.inventories.id IS 'PK of inventory record';
COMMENT ON COLUMN public.inventories.warehouse_id IS 'FK on warehouse record';
COMMENT ON COLUMN public.inventories.product_id IS 'FK on product record';
COMMENT ON COLUMN public.inventories.actual_quantity IS 'actual quantity of a product';
COMMENT ON COLUMN public.inventories.available_quantity IS 'available quantity of a product for new reservations or purchases';
