package new_test_allergy_2;

import java.util.Scanner;
    
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        // Prompt the user to choose the database URL
        System.out.println("Enter the database URL (or press Enter to use the default database):");
        String userInput = input.nextLine();

        // Set the database URL in the DatabaseConnection class
        DatabaseConnection.setUrl(userInput);
        
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
