class Teacher {
    constructor(id, name, email, dob, discipline, campus, contact, gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.discipline = discipline;
        this.campus = campus;
        this.contact = contact;
        this.gender = gender;
    }

    static async createTeacher(db, teacherData) {
        const { name, email, dob, discipline, campus, contact, gender } = teacherData;
        const result = await db.query(
            'INSERT INTO Teacher (name, email, dob, discipline, campus, contact, gender) VALUES (?, ?, ?, ?, ?, ?, ?)',
            [name, email, dob, discipline, campus, contact, gender]
        );
        return new Teacher(result.insertId, name, email, dob, discipline, campus, contact, gender);
    }

    static async getTeacherById(db, id) {
        const [teacher] = await db.query('SELECT * FROM Teacher WHERE ID = ?', [id]);
        return teacher ? new Teacher(teacher.ID, teacher.name, teacher.email, teacher.dob, teacher.discipline, teacher.campus, teacher.contact, teacher.gender) : null;
    }

    static async updateTeacher(db, id, updatedData) {
        const { name, email, dob, discipline, campus, contact, gender } = updatedData;
        await db.query(
            'UPDATE Teacher SET name = ?, email = ?, dob = ?, discipline = ?, campus = ?, contact = ?, gender = ? WHERE ID = ?',
            [name, email, dob, discipline, campus, contact, gender, id]
        );
    }

    static async deleteTeacher(db, id) {
        await db.query('DELETE FROM Teacher WHERE ID = ?', [id]);
    }

    static async getAllTeachers(db) {
        const [teachers] = await db.query('SELECT * FROM Teacher');
        return teachers.map(teacher => new Teacher(teacher.ID, teacher.name, teacher.email, teacher.dob, teacher.discipline, teacher.campus, teacher.contact, teacher.gender));
    }
}

export default Teacher;