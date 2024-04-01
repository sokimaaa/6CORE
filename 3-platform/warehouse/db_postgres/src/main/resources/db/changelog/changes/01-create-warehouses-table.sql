-- warehouses table

CREATE TABLE public.warehouses
(
    id      varchar NOT NULL,
    address varchar NOT NULL,
    CONSTRAINT warehouses_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT
ON COLUMN public.warehouses.id IS 'PK of warehouse record';
COMMENT
ON COLUMN public.warehouses.address IS 'warehouse address';
