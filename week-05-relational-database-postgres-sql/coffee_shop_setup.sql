-- coffee_shop
-- Menu, customers, orders and order lines (10 / 4 / 8 / 14 rows). Used in instructor demos.
-- Software Development + AI | Week 5: Databases 1
--
-- HOW TO USE: create an empty database (e.g. CREATE DATABASE coffee_shop;), connect to it,
-- then run this whole script. It is safe to re-run: it drops and recreates its own tables.

DROP TABLE IF EXISTS order_items CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS menu_items CASCADE;

CREATE TABLE menu_items (
    item_id   INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    category  VARCHAR(30)  NOT NULL,
    price     NUMERIC(5,2) NOT NULL,
    calories  INTEGER,
    in_stock  BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE customers (
    customer_id  INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    email        VARCHAR(150) UNIQUE NOT NULL,
    phone        VARCHAR(20)
);

CREATE TABLE orders (
    order_id     INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    customer_id  INTEGER NOT NULL REFERENCES customers(customer_id),
    order_date   TIMESTAMP NOT NULL
);

CREATE TABLE order_items (
    order_item_id  INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id       INTEGER NOT NULL REFERENCES orders(order_id),
    item_id        INTEGER NOT NULL REFERENCES menu_items(item_id),
    quantity       INTEGER NOT NULL CHECK (quantity > 0),
    UNIQUE (order_id, item_id)
);

INSERT INTO menu_items (name, category, price, calories, in_stock) VALUES
('Espresso',         'Coffee', 3.00,   5, TRUE),
('Latte',            'Coffee', 4.50, 190, TRUE),
('Cappuccino',       'Coffee', 4.25, 120, TRUE),
('Cold Brew',        'Coffee', 4.75,   5, TRUE),
('Green Tea',        'Tea',    3.25,   0, TRUE),
('Chai Latte',       'Tea',    4.50, 240, TRUE),
('Blueberry Muffin', 'Pastry', 3.50, 410, TRUE),
('Croissant',        'Pastry', 3.75, 270, TRUE),
('Bagel',            'Pastry', 2.95, NULL, FALSE),
('Orange Juice',     'Other',  3.95, NULL, TRUE);

INSERT INTO customers (name, email, phone) VALUES
('Ngozi Adeyemi', 'ngozi@example.com',  '555-0101'),
('Marcus Lee',    'marcus@example.com', '555-0102'),
('Sofia Rossi',   'sofia@example.com',  '555-0103'),
('Tunde Bakare',  'tunde@example.com',  NULL);

INSERT INTO orders (customer_id, order_date) VALUES
(1, '2026-10-01 08:15'),
(2, '2026-10-01 09:40'),
(1, '2026-10-02 08:20'),
(3, '2026-10-02 12:05'),
(1, '2026-10-03 08:10'),
(2, '2026-10-03 10:30'),
(3, '2026-10-04 11:15'),
(1, '2026-10-05 08:05');

INSERT INTO order_items (order_id, item_id, quantity) VALUES
(1, 2, 1), (1, 8, 1),
(2, 1, 1),
(3, 4, 1),
(4, 6, 1), (4, 7, 1),
(5, 3, 1),
(6, 2, 2), (6, 8, 2),
(7, 5, 1), (7, 10, 1),
(8, 2, 1), (8, 7, 2), (8, 1, 1);
