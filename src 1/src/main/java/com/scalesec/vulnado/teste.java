import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ContractManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input fields for contract details
        System.out.println("Enter Contract Name:");
        String contractName = scanner.nextLine();

        System.out.println("Enter Parties Involved (comma-separated):");
        String partiesInvolved = scanner.nextLine();

        LocalDate startDate = null;
        while (startDate == null) {
            System.out.println("Enter Start Date (YYYY-MM-DD):");
            String startDateInput = scanner.nextLine();
            try {
                startDate = LocalDate.parse(startDateInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        LocalDate expirationDate = null;
        while (expirationDate == null) {
            System.out.println("Enter Expiration Date (YYYY-MM-DD):");
            String expirationDateInput = scanner.nextLine();
            try {
                expirationDate = LocalDate.parse(expirationDateInput);
                if (expirationDate.isBefore(startDate)) {
                    System.out.println("Expiration date cannot be before start date. Please re-enter.");
                    expirationDate = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        System.out.println("Enter Notes (optional):");
        String notes = scanner.nextLine();

        // Validate required fields
        if (contractName.isEmpty() || partiesInvolved.isEmpty()) {
            System.out.println("Error: Contract Name and Parties Involved are required fields.");
            return;
        }

        // Save contract details (for now, we just print them to simulate saving to a database)
        System.out.println("\nContract Details Saved Successfully:");
        System.out.println("Contract Name: " + contractName);
        System.out.println("Parties Involved: " + partiesInvolved);
        System.out.println("Start Date: " + startDate);
        System.out.println("Expiration Date: " + expirationDate);
        System.out.println("Notes: " + (notes.isEmpty() ? "None" : notes));

        scanner.close();
    }
}