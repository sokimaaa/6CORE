-- products table

CREATE TABLE public.products (
                                 id varchar NOT NULL,
                                 "name" varchar NOT NULL,
                                 description varchar NULL,
                                 image varchar NULL,
                                 price bigint NOT NULL,
                                 category varchar NULL,
                                 CONSTRAINT products_pk PRIMARY KEY (id),
                                 CONSTRAINT products_price_check CHECK (price>=0)
);

-- Column comments

COMMENT ON COLUMN public.products.id IS 'PK of product record';
COMMENT ON COLUMN public.products."name" IS 'product name';
COMMENT ON COLUMN public.products.description IS 'product description';
COMMENT ON COLUMN public.products.image IS 'product image location path';
COMMENT ON COLUMN public.products.price IS 'price of unit of product';
COMMENT ON COLUMN public.products.category IS 'category of product';
