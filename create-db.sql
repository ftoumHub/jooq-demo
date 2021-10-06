CREATE SCHEMA records;

CREATE TABLE records.customers (
                      customer_id uuid primary key,
                      firstname varchar(255),
                      surname varchar(255),
                      age int
);