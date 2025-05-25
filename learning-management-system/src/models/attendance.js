class Attendance {
    constructor(db) {
        this.db = db;
    }

    async recordAttendance(rollNumber, sectionTitle, courseCode, semesterTitle, date, status) {
        const query = `
            INSERT INTO Attendance (date, status, rollnum, sectitle, courseCode, semTitle)
            VALUES (?, ?, ?, ?, ?, ?)
        `;
        const values = [date, status, rollNumber, sectionTitle, courseCode, semesterTitle];
        await this.db.run(query, values);
    }

    async getAttendance(rollNumber, sectionTitle, courseCode, semesterTitle) {
        const query = `
            SELECT date, status
            FROM Attendance
            WHERE rollnum = ? AND sectitle = ? AND courseCode = ? AND semTitle = ?
        `;
        const values = [rollNumber, sectionTitle, courseCode, semesterTitle];
        return await this.db.all(query, values);
    }

    async getAttendanceByDate(rollNumber, date) {
        const query = `
            SELECT sectitle, courseCode, semTitle, status
            FROM Attendance
            WHERE rollnum = ? AND date = ?
        `;
        const values = [rollNumber, date];
        return await this.db.all(query, values);
    }
}

module.exports = Attendance;