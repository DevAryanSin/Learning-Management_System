# Learning Management System

## Overview
This project is a Learning Management System (LMS) designed to manage student and teacher records, attendance, marks, and grades. It provides a structured way to store and retrieve educational data.

## Project Structure
The project is organized into the following directories:

- **db**: Contains database-related files.
  - **migrations**: SQL files for creating and managing the database schema.
  - **seed**: SQL files for inserting sample data into the database.
  - **README.md**: Documentation for database setup and management.

- **src**: Contains the application source code.
  - **config**: Configuration files for the application.
  - **models**: Contains model files for students, teachers, attendance, marks, and grades.
  - **app.js**: The main entry point of the application.

- **package.json**: Configuration file for npm, listing dependencies and scripts.

## Setup Instructions

### Database Setup
1. Navigate to the `db` directory.
2. Run the migration script to create the initial database schema:
   ```bash
   sqlite3 your_database_name.db < migrations/initial_schema.sql
   ```
3. Seed the database with sample data:
   ```bash
   sqlite3 your_database_name.db < seed/sample_data.sql
   ```

### Application Setup
1. Install the necessary dependencies:
   ```bash
   npm install
   ```
2. Configure the database connection in `src/config/database.js`.
3. Start the application:
   ```bash
   node src/app.js
   ```

## Usage
- The application allows for managing student and teacher records, tracking attendance, and recording marks and grades.
- Refer to the individual model files in `src/models` for specific methods and functionalities.

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License.