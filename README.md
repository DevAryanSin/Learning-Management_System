# JavaFX Learning Management System (LMS)

A modern, modular, and professional Learning Management System (LMS) built with JavaFX and SQLite. This project provides a full-featured platform for students and teachers, including course management, enrollment, attendance, marks, and reporting, all with a beautiful, consistent UI.

---

## Features

### For Students
- **Login Dashboard**: Secure student login and personalized dashboard
- **Course Enrollment**: Browse and enroll in available courses
- **Attendance Tracking**: View attendance records for all enrolled courses
- **Marks & Reports**: Access marks, grades, and final reports in a clear, visual format

### For Teachers
- **Login Dashboard**: Secure teacher login and dashboard
- **Course Management**: Create, edit, and manage courses
- **Student Enrollment**: View and manage enrolled students
- **Attendance Management**: Mark and review student attendance
- **Marks Entry**: Enter and update student marks
- **Final Reports**: Generate and review student performance reports

### General
- **Modern UI**: Modular, professional, and consistent design using JavaFX and FXML
- **Database Integration**: SQLite backend with DAO pattern for clean data access
- **Reusable Components**: Centralized DB utility, modular controllers, and FXML screens

---

## Screenshots

> _Add screenshots of the main dashboards, course management, and reports here for best presentation._

---

## Project Structure

```
├── lms.db                        # SQLite database file
├── src/
│   └── hellofx/
│       ├── Main.java             # Application entry point
│       ├── controllers/          # All JavaFX controllers
│       ├── fxml/                 # All FXML UI files
│       ├── icons/                # UI icons
│       ├── model/                # Data models and DAOs
│       └── resources/            # Additional resources
├── lib/                          # JavaFX and SQLite JARs
├── learning-management-system/   # (Optional) Node.js/other backend or migration scripts
│   ├── db/
│   │   ├── migrations/           # SQL schema
│   │   └── seed/                 # Sample data
│   └── ...
└── README.md                     # This file
```

---

## Getting Started

### Prerequisites
- Java 11 or higher
- JavaFX SDK (included in `lib/`)
- SQLite JDBC driver (included in `lib/`)

### Setup
1. **Clone the repository**
2. **Ensure JavaFX and SQLite JARs are in your classpath**
3. **Initialize the database**
   - Use the provided SQL scripts in `learning-management-system/db/migrations/initial_schema.sql` and `learning-management-system/db/seed/sample_data.sql` to create and populate `lms.db`.
   - Or use the prebuilt `lms.db` in the project root.
4. **Build and Run**
   - Compile the project:
     ```powershell
     javac -cp "lib/*;src" -d bin src/hellofx/Main.java
     ```
   - Run the application:
     ```powershell
     java -cp "lib/*;bin" hellofx.Main
     ```

---

## Database
- **Schema**: See `learning-management-system/db/migrations/initial_schema.sql`
- **Sample Data**: See `learning-management-system/db/seed/sample_data.sql`
- **Connection Utility**: `src/hellofx/controllers/DBUtil.java` provides a reusable connection method
- **DAO Example**: `src/hellofx/model/StudentsDAO.java` for student data access

---

## Customization & Extensibility
- **FXML UI**: Easily modify or extend UI screens in `src/hellofx/fxml/`
- **Controllers**: Add new features by creating new controllers in `src/hellofx/controllers/`
- **Database**: Add new tables or fields via migration scripts

---

## Credits
- JavaFX (UI framework)
- SQLite (Database)
- Ikonli (Icon packs)

---

## License
MIT License. See [LICENSE](LICENSE) for details.

---

## Contact
For questions or contributions, open an issue or pull request on GitHub.

---

## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
