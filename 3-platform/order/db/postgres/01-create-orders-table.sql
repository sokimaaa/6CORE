-- orders table

CREATE TABLE IF NOT EXISTS public.orders (
    id          varchar     NOT NULL,
    created_at  timestamp   NOT NULL DEFAULT NOW(),
    total       bigint      NOT NULL,
    status      varchar     NOT NULL DEFAULT 'New',
    CONSTRAINT orders_pk PRIMARY KEY (id)
);

-- Column checks

ALTER TABLE public.orders
    ADD CONSTRAINT orders_total_check CHECK (total >= 0);

-- Column comments

COMMENT
    ON COLUMN public.orders.id IS 'PK of order record';
COMMENT
    ON COLUMN public.orders.created_at IS 'date and time of order creation';
COMMENT
    ON COLUMN public.orders.total IS 'total price of the order';
COMMENT
    ON COLUMN public.orders.status IS 'order current status, at creation set to New';
