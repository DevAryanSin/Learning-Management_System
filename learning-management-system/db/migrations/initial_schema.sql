CREATE TABLE IF NOT EXISTS "Student" (
    "rollNumber" varchar PRIMARY KEY,
    "batch" int NOT NULL,
    "cgpa" float,
    "name" varchar NOT NULL,
    "dob" date NOT NULL,
    "discipline" varchar NOT NULL,
    "campus" varchar NOT NULL,
    "contact" varchar,
    "gender" char NOT NULL,
    "email" varchar UNIQUE NOT NULL,
    "password" varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS "Teacher" (
    "ID" int PRIMARY KEY,
    "salary" integer,
    "email" varchar,
    "name" varchar NOT NULL,
    "dob" date NOT NULL,
    "discipline" varchar NOT NULL,
    "campus" varchar NOT NULL,
    "contact" varchar,
    "gender" char NOT NULL,
    "password" varchar DEFAULT 'password'
);

CREATE TABLE IF NOT EXISTS "Attendance" (
    "date" date,
    "status" char DEFAULT null,
    "rollnum" varchar,
    PRIMARY KEY("rollnum", "date"),
    FOREIGN KEY("rollnum") REFERENCES "Student"("rollNumber") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Marks" (
    "rollnum" varchar,
    "courseCode" varchar,
    "semTitle" varchar,
    "marksObtained" int,
    PRIMARY KEY("rollnum", "courseCode", "semTitle"),
    FOREIGN KEY("rollnum") REFERENCES "Student"("rollNumber") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Grades" (
    "rollnum" varchar,
    "courseCode" varchar,
    "semTitle" varchar,
    "grade" char,
    PRIMARY KEY("rollnum", "courseCode", "semTitle"),
    FOREIGN KEY("rollnum") REFERENCES "Student"("rollNumber") ON DELETE CASCADE
);