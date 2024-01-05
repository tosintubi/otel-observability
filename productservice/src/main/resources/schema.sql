CREATE TABLE public.product (
    id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
    product_name varchar NULL,
    price numeric NULL,
    CONSTRAINT product_pk PRIMARY KEY (id)
);