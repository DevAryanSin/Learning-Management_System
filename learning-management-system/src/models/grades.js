class Grades {
    constructor(db) {
        this.db = db; // Database connection
    }

    // Method to calculate grade based on marks
    calculateGrade(marks) {
        if (marks >= 90) return 'A';
        if (marks >= 80) return 'B';
        if (marks >= 70) return 'C';
        if (marks >= 60) return 'D';
        if (marks >= 50) return 'E';
        return 'F';
    }

    // Method to get grades for a specific student
    async getGrades(studentId) {
        const query = 'SELECT * FROM grades WHERE studentId = ?';
        const [grades] = await this.db.execute(query, [studentId]);
        return grades;
    }

    // Method to add a new grade entry
    async addGrade(studentId, subject, marks) {
        const grade = this.calculateGrade(marks);
        const query = 'INSERT INTO grades (studentId, subject, marks, grade) VALUES (?, ?, ?, ?)';
        await this.db.execute(query, [studentId, subject, marks, grade]);
    }

    // Method to update an existing grade entry
    async updateGrade(gradeId, marks) {
        const grade = this.calculateGrade(marks);
        const query = 'UPDATE grades SET marks = ?, grade = ? WHERE id = ?';
        await this.db.execute(query, [marks, grade, gradeId]);
    }

    // Method to delete a grade entry
    async deleteGrade(gradeId) {
        const query = 'DELETE FROM grades WHERE id = ?';
        await this.db.execute(query, [gradeId]);
    }
}

export default Grades;