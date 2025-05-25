const express = require('express');
const bodyParser = require('body-parser');
const databaseConfig = require('./config/database');

const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Database connection
const { Client } = require('pg');
const client = new Client(databaseConfig);

client.connect()
    .then(() => console.log('Connected to the database'))
    .catch(err => console.error('Database connection error:', err));

// Routes (to be defined later)
// app.use('/api/students', require('./models/student'));
// app.use('/api/teachers', require('./models/teacher'));
// app.use('/api/attendance', require('./models/attendance'));
// app.use('/api/marks', require('./models/marks'));
// app.use('/api/grades', require('./models/grades'));

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});