package hellofx.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:lms.db";
    private static Connection connection = null;
    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL);
            } catch (SQLException e) {
                System.out.println("Error connecting to database: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }
}