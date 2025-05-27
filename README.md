# Learning Management System (LMS)

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

> **Welcome Page**
![image](https://github.com/user-attachments/assets/29c7334c-bcbb-4a87-bca2-1cc473c37a06)
> **Student Login**
> ![image](https://github.com/user-attachments/assets/2bec90fd-8355-46e8-9217-50f723abae00)
> **Credentials Authentication**
> ![image](https://github.com/user-attachments/assets/a8f4b74c-8e60-4ef8-bce0-ef85e05d0a1a)
> **Student Dashboard**
> ![image](https://github.com/user-attachments/assets/be216237-5e95-4729-9808-d4a5195c7053)
> **Course Enrollment**
> ![image](https://github.com/user-attachments/assets/90026d38-2599-436f-b900-4dc61be52f1b)
> **Teacher Login**
> ![image](https://github.com/user-attachments/assets/562a5722-e656-42eb-b956-61e5b7c6fd02)
> **Teacher Dashboard**
> ![image](https://github.com/user-attachments/assets/33c08b01-f6a1-4b61-b7ac-2f70f3c3555c)
> **Course Management**
> ![image](https://github.com/user-attachments/assets/b9cec1ec-be3a-4b0a-894a-829039e6d5ed)
> **Assign Marks**
> ![image](https://github.com/user-attachments/assets/a07f8ce4-3f22-4859-88ce-ebdece7c82a3)
> **Assign Attendance**
> ![image](https://github.com/user-attachments/assets/60be7480-ddb7-4103-9866-753b76a959ac)
> **Manage Students**
> ![image](https://github.com/user-attachments/assets/940b94ad-3429-4b58-a913-9064c901d1e0)
> **Generate Report**
> ![image](https://github.com/user-attachments/assets/f0278615-947d-41d4-9a4e-16826cbe0762)










## Database Connectivity
*jdbc for connectivity with MySQL*
![image](https://github.com/user-attachments/assets/6951ab7e-7e2a-46c7-9dd5-3d78d4875874)

## Database
***lms.db storing credentials***
![image](https://github.com/user-attachments/assets/ff4c8ccb-67c4-45a4-a266-82c318b26b7b)




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

## Entity Relationship (ER) Diagram
![1000056226](https://github.com/user-attachments/assets/941d431c-698d-4bc3-bfea-a18a03b72303)

## UML Diagram
![1000056227](https://github.com/user-attachments/assets/dcfb5e0c-ef21-4346-9485-0f1f1c7dd6de)


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
