class Marks {
    constructor(db) {
        this.db = db;
    }

    async addMark(studentId, courseCode, mark) {
        const query = 'INSERT INTO Marks (studentId, courseCode, mark) VALUES (?, ?, ?)';
        await this.db.run(query, [studentId, courseCode, mark]);
    }

    async getMarksByStudent(studentId) {
        const query = 'SELECT * FROM Marks WHERE studentId = ?';
        return await this.db.all(query, [studentId]);
    }

    async updateMark(studentId, courseCode, mark) {
        const query = 'UPDATE Marks SET mark = ? WHERE studentId = ? AND courseCode = ?';
        await this.db.run(query, [mark, studentId, courseCode]);
    }

    async deleteMark(studentId, courseCode) {
        const query = 'DELETE FROM Marks WHERE studentId = ? AND courseCode = ?';
        await this.db.run(query, [studentId, courseCode]);
    }
}

module.exports = Marks;