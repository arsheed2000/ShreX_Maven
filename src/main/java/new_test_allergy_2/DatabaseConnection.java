package new_test_allergy_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:identifier.sqlite";

    // Method to establish a connection to the SQLite database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
