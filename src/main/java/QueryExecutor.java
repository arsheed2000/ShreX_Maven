import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryExecutor {

    public static void main(String[] args) {
        // URL for the SQLite database
        String url = "jdbc:sqlite:identifier.sqlite";
        // Title of the show for which we want to retrieve reviews
        String showTitle = "The Fugitive";

        try (Connection conn = DriverManager.getConnection(url)) {
            // SQL query to retrieve reviews for the specified show
            String sql = "SELECT r.Reviewer, r.Rating, r.Comment " +
                    "FROM Review r " +
                    "JOIN Show s ON r.ShowID = s.ShowID " +
                    "WHERE s.Title = ?";
            // Prepare the SQL statement
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Set the show title parameter
                pstmt.setString(1, showTitle);

                // Execute the query and get the result set
                try (ResultSet rs = pstmt.executeQuery()) {
                    // Iterate through the result set and print each review
                    while (rs.next()) {
                        String reviewer = rs.getString("Reviewer");
                        String rating = rs.getString("Rating");
                        String comment = rs.getString("Comment");
                        System.out.println("Reviewer: " + reviewer + ", Rating: " + rating + ", Comment: " + comment);
                    }
                }
            }
        } catch (SQLException e) {
            // Print the error message if an SQL exception occurs
            System.out.println(e.getMessage());
        }
    }
}
