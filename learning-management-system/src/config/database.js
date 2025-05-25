const { Sequelize } = require('sequelize');

const sequelize = new Sequelize('learning_management_system', 'username', 'password', {
    host: 'localhost',
    dialect: 'postgres', // or 'mysql', 'sqlite', 'mssql'
    logging: false, // Set to true for SQL query logging
});

const connectDatabase = async () => {
    try {
        await sequelize.authenticate();
        console.log('Connection to the database has been established successfully.');
    } catch (error) {
        console.error('Unable to connect to the database:', error);
    }
};

module.exports = {
    sequelize,
    connectDatabase,
};