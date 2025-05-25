class Student {
    constructor(db) {
        this.db = db;
    }

    async createStudent(studentData) {
        const { rollNumber, batch, cgpa, name, dob, discipline, campus, contact, gender, email, password } = studentData;
        const query = `INSERT INTO Student (rollNumber, batch, cgpa, name, dob, discipline, campus, contact, gender, email, password) 
                       VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)`;
        const values = [rollNumber, batch, cgpa, name, dob, discipline, campus, contact, gender, email, password];
        return await this.db.run(query, values);
    }

    async getStudentByRollNumber(rollNumber) {
        const query = `SELECT * FROM Student WHERE rollNumber = ?`;
        return await this.db.get(query, [rollNumber]);
    }

    async updateStudent(rollNumber, updatedData) {
        const { batch, cgpa, name, dob, discipline, campus, contact, gender, email, password } = updatedData;
        const query = `UPDATE Student SET batch = ?, cgpa = ?, name = ?, dob = ?, discipline = ?, campus = ?, contact = ?, gender = ?, email = ?, password = ? 
                       WHERE rollNumber = ?`;
        const values = [batch, cgpa, name, dob, discipline, campus, contact, gender, email, password, rollNumber];
        return await this.db.run(query, values);
    }

    async deleteStudent(rollNumber) {
        const query = `DELETE FROM Student WHERE rollNumber = ?`;
        return await this.db.run(query, [rollNumber]);
    }

    async getAllStudents() {
        const query = `SELECT * FROM Student`;
        return await this.db.all(query);
    }
}

export default Student;