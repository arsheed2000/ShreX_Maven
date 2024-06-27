//Thomas
import org.w3c.dom.*; // Importing DOM classes for XML parsing
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShredAndLoadXML {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:identifier.sqlite"; // SQLite database URL
        String xmlFile = "src/main/XMLData/xml_document_test"; // Path to the XML file

        try (Connection conn = DriverManager.getConnection(url)) {
            // Initialize the XML parser
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize(); // Normalize the XML structure

            // Get the list of SHOW elements from the XML
            NodeList showList = doc.getElementsByTagName("SHOW");

            // Iterate through each SHOW element
            for (int i = 0; i < showList.getLength(); i++) {
                Node showNode = showList.item(i);
                if (showNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element showElement = (Element) showNode;

                    // Extract TITLE and YEAR from the SHOW element
                    String title = showElement.getElementsByTagName("TITLE").item(0).getTextContent();
                    int year = Integer.parseInt(showElement.getElementsByTagName("YEAR").item(0).getTextContent());

                    // Insert the show into the database and get the generated ShowID
                    int showId = insertShow(conn, title, year);

                    // Get the list of AKA elements and insert each into the database
                    NodeList akaList = showElement.getElementsByTagName("AKA");
                    for (int j = 0; j < akaList.getLength(); j++) {
                        String akaName = akaList.item(j).getTextContent();
                        insertAka(conn, showId, akaName);
                    }

                    // Get the list of REVIEW elements and insert each into the database
                    NodeList reviewList = showElement.getElementsByTagName("REVIEW");
                    for (int k = 0; k < reviewList.getLength(); k++) {
                        Element reviewElement = (Element) reviewList.item(k);
                        String reviewer = reviewElement.getElementsByTagName("REVIEWER").item(0).getTextContent();
                        String rating = reviewElement.getElementsByTagName("RATING").item(0).getTextContent();
                        String comment = reviewElement.getElementsByTagName("COMMENT").item(0).getTextContent();
                        insertReview(conn, showId, reviewer, rating, comment);
                    }
                }
            }

            // Print success message
            System.out.println("Data inserted successfully.");

        } catch (Exception e) {
            // Print the stack trace if an error occurs
            e.printStackTrace();
        }
    }

    // Method to insert a show into the database and return the generated ShowID
    private static int insertShow(Connection conn, String title, int year) throws SQLException {
        String sql = "INSERT INTO Show (Title, Year) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, title); // Set title parameter
            pstmt.setInt(2, year); // Set year parameter
            pstmt.executeUpdate(); // Execute the insert statement
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); // Return the generated ShowID
                } else {
                    throw new SQLException("Failed to retrieve ShowID.");
                }
            }
        }
    }

    // Method to insert an aka name into the Aka table
    private static void insertAka(Connection conn, int showId, String akaName) throws SQLException {
        String sql = "INSERT INTO Aka (ShowID, AkaName) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, showId); // Set ShowID parameter
            pstmt.setString(2, akaName); // Set AkaName parameter
            pstmt.executeUpdate(); // Execute the insert statement
        }
    }

    // Method to insert a review into the Review table
    private static void insertReview(Connection conn, int showId, String reviewer, String rating, String comment) throws SQLException {
        String sql = "INSERT INTO Review (ShowID, Reviewer, Rating, Comment) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, showId); // Set ShowID parameter
            pstmt.setString(2, reviewer); // Set Reviewer parameter
            pstmt.setString(3, rating); // Set Rating parameter
            pstmt.setString(4, comment); // Set Comment parameter
            pstmt.executeUpdate(); // Execute the insert statement
        }
    }
}

