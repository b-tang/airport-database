import java.sql.*;
import java.util.Scanner;

public class TestManagement {
    public static void main(String[] args) {
        // User Login
        String url = "jdbc:postgresql://cs1.calstatela.edu:5432/cs4222f23hp62?sslmode=disable";
		String username = "cs4222f23hp62";
		String password = "2jQQM8yQ";

        try {
			// Creating a connection to the database
			Connection conn = DriverManager.getConnection(url, username, password);
			
			// Creating a statement object
			Statement statement = conn.createStatement();
			
			// Creating a scanner for user input
			Scanner scanner = new Scanner(System.in);
			
			// Prompting the user input for what to do
			System.out.println("What would you like to do? (ADD/REMOVE)");
			String action = scanner.nextLine();
			
			// Creating the appropriate query based on user input
			if (action.equals("ADD")) {
				System.out.println("Enter the airplanetest's id:");
				String testID = scanner.nextLine();

				System.out.println("Enter the airplanetest's registrationnumber:");
				String testRN = scanner.nextLine();

				System.out.println("Enter the airplanetest's SSN:");
				String testSSN = scanner.nextLine();

				System.out.println("Enter the airplanetest's date:");
				String testDate = scanner.nextLine();

				System.out.println("Enter the airplanetest's hoursspent:");
				String testHour = scanner.nextLine();

				System.out.println("Enter the airplanetest's score:");
				String testScore = scanner.nextLine();
				
				String query = "INSERT INTO airplanetest (testid, registrationnumber, technicianssn, testdate, hoursspent, score) VALUES ('" + testID + "', '" + testRN + "', '" + testSSN + "', '" + testDate + "', '" + testHour + "', '" + testScore + "')";
				
				// Executing the query
				statement.executeUpdate(query);
				
				System.out.println("Airplane Test added successfully!");
				
			} else if (action.equals("REMOVE")) {
				System.out.println("Enter the airplanetest's id:");
				String testID = scanner.nextLine();
				
				String query = "DELETE FROM airplanetest WHERE testid='" + testID + "'";
				
				//Executing the query
				statement.executeUpdate(query);
				
				System.out.println("Airplane Test removed successfully!");
			}
			
			// Closing the connection
			conn.close();

            // Closing the scanner
            scanner.close();
			
		} catch (SQLException e) {
            // Error Output
			System.out.println("Error: " + e.getMessage());
		}
    }
}
