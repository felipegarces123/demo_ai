# Documentation: `ContractManagerTest`

## Overview
The `ContractManagerTest` class is a suite of unit tests designed to validate the behavior of the `ContractManager` application. The tests simulate user input, capture console output, and verify that the application handles various scenarios correctly, such as valid input, invalid dates, missing fields, and empty notes.

The tests are implemented using the JUnit 5 framework (`org.junit.jupiter.api`) and make use of assertions to validate expected outcomes. Additionally, the `Mockito` library is imported but not utilized in the provided code.

---

## Key Features Tested

### 1. **Valid Input Handling**
   - **Test Method**: `ContractManager_ValidInput_ShouldSaveContractDetails`
   - **Description**: Verifies that the application correctly saves and displays contract details when valid input is provided.
   - **Assertions**:
     - Contract details are saved successfully.
     - Contract name, parties involved, start date, expiration date, and notes match the input.

### 2. **Invalid Start Date Handling**
   - **Test Method**: `ContractManager_InvalidStartDate_ShouldPromptAgain`
   - **Description**: Ensures the application prompts the user to re-enter the start date if an invalid date format is provided.
   - **Assertions**:
     - The output contains an error message: `"Invalid date format. Please use YYYY-MM-DD."`

### 3. **Expiration Date Before Start Date**
   - **Test Method**: `ContractManager_ExpirationBeforeStartDate_ShouldPromptAgain`
   - **Description**: Validates that the application detects and prompts the user when the expiration date is earlier than the start date.
   - **Assertions**:
     - The output contains an error message: `"Expiration date cannot be before start date. Please re-enter."`

### 4. **Missing Required Fields**
   - **Test Method**: `ContractManager_MissingRequiredFields_ShouldShowError`
   - **Description**: Checks that the application displays an error message when required fields (contract name and parties involved) are missing.
   - **Assertions**:
     - The output contains an error message: `"Error: Contract Name and Parties Involved are required fields."`

### 5. **Empty Notes Handling**
   - **Test Method**: `ContractManager_EmptyNotes_ShouldShowNone`
   - **Description**: Ensures that when the notes field is left empty, the application displays `"Notes: None"` in the output.
   - **Assertions**:
     - The output contains: `"Notes: None"`

---

## Implementation Details

### Simulating User Input
The `simulateUserInput` method is used to redirect standard input (`System.in`) to a `ByteArrayInputStream` containing the simulated user input. This allows the tests to mimic user interaction with the application.

```java
private void simulateUserInput(String input) {
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
}
```

### Capturing Console Output
The tests redirect standard output (`System.out`) to a custom `PrintStream` that appends the output to a `StringBuilder`. This enables the tests to capture and analyze the application's console output.

```java
StringBuilder output = new StringBuilder();
System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
    @Override
    public void write(int b) {
        output.append((char) b);
    }
}));
```

### Assertions
The tests use JUnit's `assertTrue` and `assertEquals` methods to validate the application's behavior. These assertions ensure that the output matches the expected results for each test scenario.

---

## Insights

1. **Test Coverage**:
   - The tests cover a wide range of scenarios, including valid input, invalid input, and edge cases (e.g., empty notes). This ensures robust validation of the `ContractManager` application.

2. **Error Handling**:
   - The application appears to handle errors gracefully by prompting the user to correct invalid input. This is evident from the tests for invalid dates and missing fields.

3. **Input Validation**:
   - The tests highlight the importance of validating user input, such as checking date formats and ensuring required fields are provided.

4. **Console-Based Interaction**:
   - The application relies on console-based input and output, which is effectively tested using input simulation and output capture.

5. **Potential Enhancements**:
   - The `Mockito` library is imported but not used in the provided tests. If the application has dependencies or external interactions, `Mockito` could be utilized to mock those components.
   - The tests could be extended to include scenarios such as invalid party names or excessively long input values.

6. **Reusability**:
   - The `simulateUserInput` and output capture mechanisms are reusable across multiple tests, promoting code maintainability and reducing redundancy.

---

## Dependencies

| Dependency                | Purpose                                      |
|---------------------------|----------------------------------------------|
| `org.junit.jupiter.api`   | Provides the JUnit 5 testing framework.      |
| `org.mockito.Mockito`     | Imported but not utilized in the tests.      |
| `java.io`                 | Used for input/output redirection.           |
| `java.time.LocalDate`     | Used for date-related operations.            |

---

## File Metadata

| Attribute   | Value               |
|-------------|---------------------|
| File Name   | `teste_tests.java`  |
