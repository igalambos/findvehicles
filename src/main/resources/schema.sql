DROP TABLE cars IF EXISTS CASCADE;
DROP TABLE prices IF EXISTS CASCADE;
DROP TABLE other_info IF EXISTS CASCADE;

CREATE TABLE cars
(
    id      INTEGER IDENTITY PRIMARY KEY,
    make    VARCHAR(80),
    model   VARCHAR(80),
    mileage BIGINT,
    term    BIGINT
);
CREATE INDEX cars_make ON cars (make);

CREATE TABLE prices
(
    id     INTEGER IDENTITY PRIMARY KEY,
    amount FLOAT   NOT NULL,
    car_id INTEGER NOT NULL

);
CREATE INDEX prices_car_id ON prices (car_id);

ALTER TABLE prices
    ADD CONSTRAINT fk_cars_prices_cars FOREIGN KEY (car_id) REFERENCES cars (id);

CREATE TABLE other_info
(
    id     INTEGER IDENTITY PRIMARY KEY,
    name   VARCHAR(80),
    value  VARCHAR(80),
    car_id INTEGER NOT NULL
);
CREATE INDEX other_info_car_id ON other_info (car_id);

ALTER TABLE other_info
    ADD CONSTRAINT fk_cars_other_info_cars FOREIGN KEY (car_id) REFERENCES cars (id);





