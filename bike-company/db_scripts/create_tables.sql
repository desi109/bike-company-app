CREATE SEQUENCE hibernate_sequence
  MINVALUE 1
  START 4;

CREATE TABLE bike
(
    id SERIAL PRIMARY KEY,
    contact boolean default true NOT NULL,
    email varchar(100) NULL,
    model varchar(120) NOT NULL,
    name varchar(100) NOT NULL,
    phone varchar(20) NOT NUll,
    purchase_date date NOT NULL,
    purchase_price numeric(8,2) NULL,
    serial_number varchar(20) NOT NULL
);
