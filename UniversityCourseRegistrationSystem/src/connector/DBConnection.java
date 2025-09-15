package connector;

import java.sql.*;

public class DBConnection {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/javatraining";
    private static final String USER = "atomuser";
    private static final String PASS = "root";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASS);
    }
}
