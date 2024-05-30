import java.sql.Connection; // Import for managing database connections
import java.sql.DriverManager; // Import for getting database connection
import java.sql.SQLException; // Import for handling SQL exceptions
import java.sql.Statement; // Import for executing SQL statements

public class CreateTables {

    public static void main(String[] args) {
        // URL for connecting to the MySQL database
        String url = "jdbc:sqlite:identifier.sqlite"; // Ensure the database URL is correct and accessible
        // SQL statement to create the Show table
        String createShowTable = "CREATE TABLE IF NOT EXISTS Show (" +
                "ShowID INTEGER PRIMARY KEY AUTOINCREMENT," + // Primary key with auto-increment
                "Title TEXT," + // Column for show title
                "Year INTEGER" + // Column for show year
                ");";

        // SQL statement to create the Aka table
        String createAkaTable = "CREATE TABLE IF NOT EXISTS Aka (" +
                "AkaID INTEGER PRIMARY KEY AUTOINCREMENT," + // Primary key with auto-increment
                "ShowID INTEGER," + // Foreign key referencing ShowID from the Show table
                "AkaName TEXT," + // Column for alternate name
                "FOREIGN KEY (ShowID) REFERENCES Show(ShowID)" + // Define the foreign key constraint
                ");";

        // SQL statement to create the Review table
        String createReviewTable = "CREATE TABLE IF NOT EXISTS Review (" +
                "ReviewID INTEGER PRIMARY KEY AUTOINCREMENT," + // Primary key with auto-increment
                "ShowID INTEGER," + // Foreign key referencing ShowID from the Show table
                "Reviewer TEXT," + // Column for reviewer's name
                "Rating TEXT," + // Column for rating
                "Comment TEXT," + // Column for comment
                "FOREIGN KEY (ShowID) REFERENCES Show(ShowID)" + // Define the foreign key constraint
                ");";

        // Try-with-resources statement to ensure the connection and statement are closed automatically
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Execute the SQL statements to create the tables
            stmt.execute(createShowTable);
            stmt.execute(createAkaTable);
            stmt.execute(createReviewTable);

            // Print success message if tables are created successfully
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            // Print the error message if an SQL exception occurs
            System.out.println(e.getMessage());
        }
    }
}
