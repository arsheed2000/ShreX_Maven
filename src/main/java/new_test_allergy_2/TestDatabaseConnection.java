package new_test_allergy_2;

import new_test_allergy_2.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabaseConnection {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Attempt to establish a connection using the DatabaseConnection class
            connection = DatabaseConnection.getConnection();

            // If connection is successful, print a success message
            if (connection != null) {
                System.out.println("Database connection established successfully!");
            } else {
                System.out.println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            // Print any SQL exceptions that occur during connection attempt
            e.printStackTrace();
        } finally {
            // Close the connection in the finally block to ensure it's always closed
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
