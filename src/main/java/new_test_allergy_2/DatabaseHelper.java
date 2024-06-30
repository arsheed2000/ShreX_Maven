package new_test_allergy_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {

    // Method to insert allergy intolerance data into the database
    public static void insertAllergyIntolerance(String clinicalStatus, String verificationStatus, String category, String criticality,
                                                String code, String display, String codeText, String patientReference, String patientDisplay,
                                                String reactionSeverity, String manifestationCode, String manifestationDisplay, String manifestationText) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            // SQL query to insert data into the AllergyIntolerance table
            String insertQuery = "INSERT INTO AllergyIntolerance (ClinicalStatus, VerificationStatus, Category, Criticality, " +
                    "Code, Display, CodeText, PatientReference, PatientDisplay, " +
                    "ReactionSeverity, ManifestationCode, ManifestationDisplay, ManifestationText) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Prepare the SQL statement with parameters
            PreparedStatement pstmt = connection.prepareStatement(insertQuery);
            pstmt.setString(1, clinicalStatus);
            pstmt.setString(2, verificationStatus);
            pstmt.setString(3, category);
            pstmt.setString(4, criticality);
            pstmt.setString(5, code);
            pstmt.setString(6, display);
            pstmt.setString(7, codeText);
            pstmt.setString(8, patientReference);
            pstmt.setString(9, patientDisplay);
            pstmt.setString(10, reactionSeverity);
            pstmt.setString(11, manifestationCode);
            pstmt.setString(12, manifestationDisplay);
            pstmt.setString(13, manifestationText);

            // Execute the insert operation
            pstmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }
    }
}
