import java.sql.*;
import java.util.Scanner;

public class DisplayInfo {
    public static void main(String[] args) {
        // User Login
        String url = "jdbc:postgresql://cs1.calstatela.edu:5432/cs4222f23hp62?sslmode=disable";
		String username = "cs4222f23hp62";
		String password = "2jQQM8yQ";

        try {
            // Creating a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);

            // Creating a scrollable statement object
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // Creating a scanner for user input
            Scanner scanner = new Scanner(System.in);

            // Prompting the user for the table to view
            System.out.println("What table would you like to view? (AIRPLANE/AIRPLANEMODEL/AIRPLANETEST/EMPLOYEE/TECHNICIAN/TECHNICIANEXPERTISE/TRAFFICCONTROLLER)");
            String table = scanner.nextLine();

            // Printing the table information
            System.out.println("\nTable: " + table);
            String query = "SELECT * FROM " + table;
            try (ResultSet resultSet = statement.executeQuery(query)) {
                ResultSetMetaData metaData = resultSet.getMetaData();

                // Calculate column widths
                int columnCount = metaData.getColumnCount();
                int[] columnWidths = new int[columnCount];

                // Initialize column widths with column names length
                for (int i = 1; i <= columnCount; i++) {
                    columnWidths[i - 1] = metaData.getColumnName(i).length();
                }

                // Update column widths based on data
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String value = resultSet.getString(i);
                        columnWidths[i - 1] = Math.max(columnWidths[i - 1], value.length());
                    }
                }

                // Print column names
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    System.out.printf("%-" + (columnWidths[i - 1] + 2) + "s", columnName);
                }
                System.out.println();

                // Move the cursor back to the beginning
                resultSet.beforeFirst();
                
                // Print table data
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        String value = resultSet.getString(i);
                        System.out.printf("%-" + (columnWidths[i - 1] + 2) + "s", value);
                    }
                    System.out.println();
                }
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
