-- Messy starter table for the normalization beginner guide.
-- Run once in a practice database. Compatible with PostgreSQL and SQLite.
-- This deliberately unnormalized design is an exercise, not a final schema.
-- No existing table or data is deleted. If students_messy exists, use a fresh
-- practice database or choose a different table name throughout this script.

CREATE TABLE students_messy (
    student_id      INTEGER PRIMARY KEY,
    student_name    VARCHAR(100) NOT NULL,
    department_id   VARCHAR(10) NOT NULL,
    department_name VARCHAR(100) NOT NULL,
    courses         VARCHAR(500) NOT NULL
);

-- One row per student. The courses column intentionally contains a list.
INSERT INTO students_messy
    (student_id, student_name, department_id, department_name, courses)
VALUES
    (1, 'Ada', 'D1', 'Computing', 'C1: Databases, C2: Programming'),
    (2, 'Ben', 'D1', 'Computing', 'C1: Databases');

-- Inspect the messy data. Expected result: two student rows.
SELECT student_id, student_name, department_id, department_name, courses
FROM students_messy
ORDER BY student_id;

-- Demonstration only: searching a list requires text matching.
-- Expected result: Ada. This does not establish an exact course ID match.
SELECT student_id, student_name, courses
FROM students_messy
WHERE courses LIKE '%Programming%'
ORDER BY student_id;

-- Practice assumptions:
-- Each student belongs to one department.
-- Each course ID has one official course name.
-- A student can enroll in a given course only once.
--
-- Your tasks:
-- 1. 1NF: Split courses into individual enrollment rows.
--    Identify each enrollment with (student_id, course_id).
-- 2. 2NF: Separate Students, Courses and Enrollments.
-- 3. 3NF: Move department names into Departments.
-- Expected final facts: two students, two courses, three enrollments,
-- and one department. Preserve all of them during normalization.
