package new_test_allergy_2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    public static void main(String[] args) {
        // Attempt to create database tables for storing allergy intolerance data

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement()) {

            // SQL statement to drop the AllergyIntolerance table if it exists
            String dropAllergyIntoleranceTable = "DROP TABLE IF EXISTS AllergyIntolerance;";

            // Execute the SQL statement to drop the table
            stmt.execute(dropAllergyIntoleranceTable);

            // SQL statement to create AllergyIntolerance table if it doesn't exist
            String createAllergyIntoleranceTable = "CREATE TABLE IF NOT EXISTS AllergyIntolerance (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "ClinicalStatus TEXT," +
                    "VerificationStatus TEXT," +
                    "Category TEXT," +
                    "Criticality TEXT," +
                    "Code TEXT," +
                    "Display TEXT," +
                    "CodeText TEXT," +
                    "PatientReference TEXT," +
                    "PatientDisplay TEXT," +
                    "ReactionSeverity TEXT," +
                    "ManifestationCode TEXT," +
                    "ManifestationDisplay TEXT," +
                    "ManifestationText TEXT" +
                    ");";

            // Execute the SQL statement to create the table
            stmt.execute(createAllergyIntoleranceTable);

            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());
        }
    }
}
