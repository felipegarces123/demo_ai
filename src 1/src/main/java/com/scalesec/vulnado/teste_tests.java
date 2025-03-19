import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContractManagerTest {

    private void simulateUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    public void ContractManager_ValidInput_ShouldSaveContractDetails() {
        // Simulate valid user input
        String input = "Test Contract\nParty A, Party B\n2023-01-01\n2023-12-31\nSome notes\n";
        simulateUserInput(input);

        // Capture console output
        StringBuilder output = new StringBuilder();
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                output.append((char) b);
            }
        }));

        // Execute main method
        ContractManager.main(new String[]{});

        // Assert output contains expected details
        assertTrue(output.toString().contains("Contract Details Saved Successfully"), "Contract details should be saved successfully.");
        assertTrue(output.toString().contains("Contract Name: Test Contract"), "Contract name should match.");
        assertTrue(output.toString().contains("Parties Involved: Party A, Party B"), "Parties involved should match.");
        assertTrue(output.toString().contains("Start Date: 2023-01-01"), "Start date should match.");
        assertTrue(output.toString().contains("Expiration Date: 2023-12-31"), "Expiration date should match.");
        assertTrue(output.toString().contains("Notes: Some notes"), "Notes should match.");
    }

    @Test
    public void ContractManager_InvalidStartDate_ShouldPromptAgain() {
        // Simulate invalid start date followed by valid input
        String input = "Test Contract\nParty A, Party B\ninvalid-date\n2023-01-01\n2023-12-31\nSome notes\n";
        simulateUserInput(input);

        // Capture console output
        StringBuilder output = new StringBuilder();
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                output.append((char) b);
            }
        }));

        // Execute main method
        ContractManager.main(new String[]{});

        // Assert output contains error message for invalid date
        assertTrue(output.toString().contains("Invalid date format. Please use YYYY-MM-DD."), "Should prompt for invalid date format.");
    }

    @Test
    public void ContractManager_ExpirationBeforeStartDate_ShouldPromptAgain() {
        // Simulate expiration date before start date
        String input = "Test Contract\nParty A, Party B\n2023-01-01\n2022-12-31\n2023-12-31\nSome notes\n";
        simulateUserInput(input);

        // Capture console output
        StringBuilder output = new StringBuilder();
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                output.append((char) b);
            }
        }));

        // Execute main method
        ContractManager.main(new String[]{});

        // Assert output contains error message for expiration date
        assertTrue(output.toString().contains("Expiration date cannot be before start date. Please re-enter."), "Should prompt for expiration date before start date.");
    }

    @Test
    public void ContractManager_MissingRequiredFields_ShouldShowError() {
        // Simulate missing required fields
        String input = "\n\n2023-01-01\n2023-12-31\nSome notes\n";
        simulateUserInput(input);

        // Capture console output
        StringBuilder output = new StringBuilder();
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                output.append((char) b);
            }
        }));

        // Execute main method
        ContractManager.main(new String[]{});

        // Assert output contains error message for missing fields
        assertTrue(output.toString().contains("Error: Contract Name and Parties Involved are required fields."), "Should show error for missing required fields.");
    }

    @Test
    public void ContractManager_EmptyNotes_ShouldShowNone() {
        // Simulate empty notes
        String input = "Test Contract\nParty A, Party B\n2023-01-01\n2023-12-31\n\n";
        simulateUserInput(input);

        // Capture console output
        StringBuilder output = new StringBuilder();
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                output.append((char) b);
            }
        }));

        // Execute main method
        ContractManager.main(new String[]{});

        // Assert output contains "None" for notes
        assertTrue(output.toString().contains("Notes: None"), "Notes should be displayed as 'None' when empty.");
    }
}
