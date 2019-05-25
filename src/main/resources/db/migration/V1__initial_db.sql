CREATE TYPE status AS ENUM (
    'OPEN',
    'FINALIZED'
);

CREATE TABLE customer
(
   id SERIAL PRIMARY KEY,
   created_at timestamp with time zone NOT NULL,
   modified_at timestamp with time zone NOT NULL,
   name VARCHAR(100) NOT NULL
);

CREATE TABLE product (
   id SERIAL PRIMARY KEY,
   created_at timestamp with time zone NOT NULL,
   modified_at timestamp with time zone NOT NULL,
   name VARCHAR(100) NOT NULL,
   description VARCHAR(500) NULL
);

CREATE TABLE "order" (
    id SERIAL PRIMARY KEY,
    created_at timestamp with time zone NOT NULL,
    modified_at timestamp with time zone NOT NULL,
    status status NOT NULL,
    customer_id integer references customer (id)
);

CREATE TABLE order_product (
    id SERIAL PRIMARY KEY,
    created_at timestamp with time zone NOT NULL,
    modified_at timestamp with time zone NOT NULL,
    order_id integer references "order" (id),
    product_id integer references product (id),
    amount int NOT NULL,
    price money NOT NULL,
    discount numeric(2) NOT NULL
);

CREATE TABLE product_stock (
    id SERIAL PRIMARY KEY,
    created_at timestamp with time zone NOT NULL,
    modified_at timestamp with time zone NOT NULL,
    product_id integer references product (id),
    amount int NOT NULL,
    price money NOT NULL
);


CREATE TABLE restock (
    id SERIAL PRIMARY KEY,
    created_at timestamp with time zone NOT NULL,
    modified_at timestamp with time zone NOT NULL,
    status status NOT NULL,
    product_id integer references product (id),
    amount int NOT NULL
);



