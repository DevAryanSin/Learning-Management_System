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
> ![image](https://github.com/user-attachments/assets/c2617107-73f7-440a-a5aa-641b35ef79c7)
> **Student Login**
> ![image](https://github.com/user-attachments/assets/dafa4548-41f7-452c-a362-f1e2970f6f5e)
> **Credentials Authentication**
>![image](https://github.com/user-attachments/assets/eab95dff-e3d0-42b9-bd41-79411d2ff32e)
> **Create Account**
> ![image](https://github.com/user-attachments/assets/ef914de9-57c2-48ae-9a7d-8e343a2e53f1)
> **Forgot Password**
> ![image](https://github.com/user-attachments/assets/838c3367-0374-4c7d-a3da-9ccb6a121399)
> **Password Reset**
> ![image](https://github.com/user-attachments/assets/6bfe29a4-d7b3-4f7e-b286-b16c8b651428)
> **Student Dashboard**
> ![image](https://github.com/user-attachments/assets/b270f39a-046a-4423-99af-35bf5c7dd337)
> **Course Enrollment**
> ![image](https://github.com/user-attachments/assets/316cb50a-8912-41de-a717-4c886d5863eb)
> **Teacher Login**
> ![image](https://github.com/user-attachments/assets/069a9204-6d77-4300-80fb-265d5434cc08)
> **Teacher Dashboard**
> ![image](https://github.com/user-attachments/assets/4f4b5f17-1d6c-4866-879b-456906b0852d)
> **Course Management**
> ![image](https://github.com/user-attachments/assets/492a52a9-9250-4ce1-a49a-6601995d88bd)
> **Assign Marks**
> ![image](https://github.com/user-attachments/assets/119e0361-d507-4eb3-9007-d7a510ea01f7)
> **Assign Attendance**
> ![image](https://github.com/user-attachments/assets/1ad4d29f-f6cd-460b-bd0b-3ef45acd7d00)
> **Manage Students**
> ![image](https://github.com/user-attachments/assets/3d7b42e5-913d-45d3-b417-34b512f49da3)
> **Generate Report**
> ![image](https://github.com/user-attachments/assets/d0888ea5-102a-4ea3-ad09-093ae856b2fb)










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
