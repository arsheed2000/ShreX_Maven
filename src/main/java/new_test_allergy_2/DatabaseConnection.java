package new_test_allergy_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url;

    // Method to establish a connection to the SQLite database
    public static Connection getConnection() throws SQLException {
        if (url == null || url.isEmpty()) {
            throw new SQLException("Database URL is not set.");
        }
        return DriverManager.getConnection(url);
    }

    // Method to set the database URL
    public static void setUrl(String url) {
        DatabaseConnection.url = url;
    }
}
