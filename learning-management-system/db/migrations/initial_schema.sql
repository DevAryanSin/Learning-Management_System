CREATE TABLE IF NOT EXISTS "Student" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "rollNumber" varchar UNIQUE NOT NULL,
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
    "ID" INTEGER PRIMARY KEY AUTOINCREMENT,
    "salary" integer,
    "email" varchar UNIQUE NOT NULL,
    "name" varchar NOT NULL,
    "dob" date NOT NULL,
    "discipline" varchar NOT NULL,
    "campus" varchar NOT NULL,
    "contact" varchar,
    "gender" char NOT NULL,
    "password" varchar DEFAULT 'password'
);

-- Courses table: teachers can edit, students can enroll
CREATE TABLE IF NOT EXISTS "Course" (
    "courseCode" varchar PRIMARY KEY,
    "title" varchar NOT NULL,
    "description" text,
    "teacherID" INTEGER,
    FOREIGN KEY("teacherID") REFERENCES "Teacher"("ID") ON DELETE SET NULL
);

-- Enrollment: students enroll in courses
CREATE TABLE IF NOT EXISTS "Enrollment" (
    "rollnum" varchar,
    "courseCode" varchar,
    "enrollDate" date DEFAULT CURRENT_DATE,
    PRIMARY KEY("rollnum", "courseCode"),
    FOREIGN KEY("rollnum") REFERENCES "Student"("rollNumber") ON DELETE CASCADE,
    FOREIGN KEY("courseCode") REFERENCES "Course"("courseCode") ON DELETE CASCADE
);

-- Attendance: teachers can edit, students can view
CREATE TABLE IF NOT EXISTS "Attendance" (
    "date" date,
    "rollnum" varchar,
    "courseCode" varchar,
    "status" char DEFAULT null,
    PRIMARY KEY("rollnum", "courseCode", "date"),
    FOREIGN KEY("rollnum") REFERENCES "Student"("rollNumber") ON DELETE CASCADE,
    FOREIGN KEY("courseCode") REFERENCES "Course"("courseCode") ON DELETE CASCADE
);

-- Marks: teachers can edit, students can view
CREATE TABLE IF NOT EXISTS "Marks" (
    "rollnum" varchar,
    "courseCode" varchar,
    "semTitle" varchar,
    "marksObtained" int,
    PRIMARY KEY("rollnum", "courseCode", "semTitle"),
    FOREIGN KEY("rollnum") REFERENCES "Student"("rollNumber") ON DELETE CASCADE,
    FOREIGN KEY("courseCode") REFERENCES "Course"("courseCode") ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS "Grades" (
    "rollnum" varchar,
    "courseCode" varchar,
    "semTitle" varchar,
    "grade" char,
    PRIMARY KEY("rollnum", "courseCode", "semTitle"),
    FOREIGN KEY("rollnum") REFERENCES "Student"("rollNumber") ON DELETE CASCADE,
    FOREIGN KEY("courseCode") REFERENCES "Course"("courseCode") ON DELETE CASCADE
);