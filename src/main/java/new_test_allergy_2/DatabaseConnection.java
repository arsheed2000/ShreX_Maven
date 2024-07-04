package new_test_allergy_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DEFAULT_URL = "jdbc:sqlite:identifier.sqlite";
    private static String url = DEFAULT_URL;

    // Method to establish a connection to the SQLite database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    // Method to set the database URL
    public static void setUrl(String url) {
        DatabaseConnection.url = (url == null || url.isEmpty()) ? DEFAULT_URL : url;
    }
}
