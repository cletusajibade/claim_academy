-- library_db
-- Books, members and loans (10 / 6 / 14 rows). Used in the Week 5 labs.
-- Software Development + AI | Week 5: Databases 1
--
-- HOW TO USE: create an empty database (e.g. CREATE DATABASE library_db;), connect to it,
-- then run this whole script. It is safe to re-run: it drops and recreates its own tables.

DROP TABLE IF EXISTS loans CASCADE;
DROP TABLE IF EXISTS members CASCADE;
DROP TABLE IF EXISTS books CASCADE;

CREATE TABLE books (
    book_id          INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title            VARCHAR(150) NOT NULL,
    author           VARCHAR(100) NOT NULL,
    publication_year INTEGER,
    genre            VARCHAR(50),
    price            NUMERIC(6,2),
    copies           INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE members (
    member_id  INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(150) UNIQUE,
    join_date  DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE loans (
    loan_id     INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    book_id     INTEGER NOT NULL REFERENCES books(book_id),
    member_id   INTEGER NOT NULL REFERENCES members(member_id),
    loan_date   DATE NOT NULL,
    return_date DATE
);

INSERT INTO books (title, author, publication_year, genre, price, copies) VALUES
('The Hobbit',                 'J.R.R. Tolkien',            1937, 'Fantasy',            12.99, 3),
('The Fellowship of the Ring', 'J.R.R. Tolkien',            1954, 'Fantasy',            14.99, 2),
('1984',                       'George Orwell',             1949, 'Dystopian',          10.99, 4),
('Animal Farm',                'George Orwell',             1945, 'Dystopian',           8.99, 2),
('Pride and Prejudice',        'Jane Austen',               1813, 'Romance',             9.99, 3),
('Emma',                       'Jane Austen',               1815, 'Romance',             9.49, 1),
('Things Fall Apart',          'Chinua Achebe',             1958, 'Historical Fiction', 11.99, 3),
('Half of a Yellow Sun',       'Chimamanda Ngozi Adichie',  2006, 'Historical Fiction', 15.99, 2),
('Clean Code',                 'Robert C. Martin',          2008, 'Technology',         37.99, 2),
('The Pragmatic Programmer',   'Andrew Hunt, David Thomas', 1999, 'Technology',         42.50, 1);

INSERT INTO members (name, email, join_date) VALUES
('Ada Okafor',   'ada.okafor@example.com',   '2025-01-15'),
('Brian Smith',  'brian.smith@example.com',  '2025-02-03'),
('Chidi Eze',    'chidi.eze@example.com',    '2025-03-21'),
('Diana Lopez',  'diana.lopez@example.com',  '2025-06-10'),
('Emeka Nwosu',  'emeka.nwosu@example.com',  '2025-08-01'),
('Fatima Bello', NULL,                       '2025-09-12');

INSERT INTO loans (book_id, member_id, loan_date, return_date) VALUES
(1, 1, '2026-09-01', '2026-09-14'),
(3, 1, '2026-09-01', '2026-09-15'),
(5, 2, '2026-09-03', '2026-09-20'),
(3, 3, '2026-09-05', '2026-09-19'),
(7, 1, '2026-09-10', '2026-09-24'),
(9, 4, '2026-09-12', '2026-10-01'),
(2, 1, '2026-09-15', NULL),
(8, 3, '2026-09-18', '2026-10-02'),
(1, 4, '2026-09-22', NULL),
(4, 2, '2026-09-25', '2026-10-05'),
(3, 5, '2026-09-28', NULL),
(7, 3, '2026-10-01', '2026-10-09'),
(9, 2, '2026-10-03', NULL),
(5, 1, '2026-10-05', '2026-10-10');

-- Check: should return 10, 6, 14
SELECT (SELECT COUNT(*) FROM books) AS books, (SELECT COUNT(*) FROM members) AS members, (SELECT COUNT(*) FROM loans) AS loans;
