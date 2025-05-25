# Database Setup and Management

This README provides instructions on how to set up and manage the database for the Learning Management System project.

## Initial Setup

1. **Database Configuration**: Ensure that your database configuration is set up correctly in `src/config/database.js`. Update the connection parameters as needed for your environment.

2. **Running Migrations**: To create the initial database schema, run the SQL commands in `db/migrations/initial_schema.sql`. This will create the necessary tables for students, teachers, attendance, marks, and grades.

   Example command (for PostgreSQL):
   ```
   psql -U username -d database_name -f db/migrations/initial_schema.sql
   ```

3. **Seeding Data**: After running the migrations, you can populate the database with sample data for testing purposes. Execute the SQL commands in `db/seed/sample_data.sql`.

   Example command (for PostgreSQL):
   ```
   psql -U username -d database_name -f db/seed/sample_data.sql
   ```

## Database Structure

The database includes the following tables:

- **Students**: Stores student information including roll number, name, contact details, and academic details.
- **Teachers**: Contains teacher information such as ID, name, contact details, and salary.
- **Attendance**: Records attendance data for students, including date and status.
- **Marks**: Manages marks for students in various subjects.
- **Grades**: Calculates and stores grades based on marks.

## Maintenance

- Regularly back up your database to prevent data loss.
- Update the schema as needed by modifying the `initial_schema.sql` file and re-running the migrations.
- Use the seed file to refresh sample data for testing after making changes to the schema.

For further assistance, refer to the documentation in the main project README or consult the relevant model files in `src/models`.