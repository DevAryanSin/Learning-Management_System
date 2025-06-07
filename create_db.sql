CREATE TABLE IF NOT EXISTS students (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    student_id TEXT NOT NULL,
    name TEXT NOT NULL,
    email TEXT NOT NULL,
    age INTEGER,
    phone TEXT,
    course TEXT
);

INSERT INTO students (student_id, name, email, age, phone, course)
VALUES ('STU001', 'John Doe', 'john.doe@example.com', 20, '1234567890', 'Computer Science');
