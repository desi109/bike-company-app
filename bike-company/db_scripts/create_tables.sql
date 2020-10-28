CREATE TABLE bike
(
    id bigint NOT NULL PRIMARY KEY,
    contact bool NOT NULL,
    email varchar(80),
    model varchar(60),
    name varchar(100),
    phone varchar(20),
    purchase_date date,
    purchase_price numeric(8,2),
    serial_number varchar
);

CREATE TABLE hibernate_sequence
(
    next_val bigint 
);
