package new_test_allergy_2;

public class Main {
    public static void main(String[] args) {
        // Step 1: Create the database tables
        System.out.println("Creating tables...");
        CreateTables.main(args);

        // Step 2: Parse the XML and insert data into the database
        System.out.println("Parsing XML and inserting data...");
        ParseXML.main(args);

        // Step 3: Test the database connection (optional)
        System.out.println("Testing database connection...");
        TestDatabaseConnection.main(args);

        System.out.println("Process completed.");
    }
}
