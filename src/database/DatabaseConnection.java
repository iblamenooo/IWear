package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/iwear_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "456789";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to database!");
            return connection;
        } catch (SQLException e) {
            System.out.println("❌ DB connection failed: " + e.getMessage());
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
            System.out.println("✅ Connection closed.");
        } catch (SQLException e) {
            System.out.println("❌ Failed to close connection: " + e.getMessage());
        }
    }
}
