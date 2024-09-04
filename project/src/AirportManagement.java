import java.sql.*;
import java.util.Scanner;

public class AirportManagement {
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
                System.out.println("Enter the airplane's model number:");
                String model_num = scanner.nextLine();
                
                System.out.println("Enter the airplane's registration number:");
                String reg_num = scanner.nextLine();
                
                String query = "INSERT INTO airplane (modelnumber, registrationnumber) VALUES ('" + model_num + "', '" + reg_num + "')";
                
                // Executing the query
                statement.executeUpdate(query);
                
                System.out.println("Airplane added successfully!");
                
            } else if (action.equals("REMOVE")) {
                System.out.println("Enter the airplane's registration number:");
                String reg_num = scanner.nextLine();
                
                String query = "DELETE FROM airplane WHERE registrationnumber = '" + reg_num + "'";
                
                // Executing the query
                statement.executeUpdate(query);
                
                System.out.println("Airplane removed successfully!");
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
