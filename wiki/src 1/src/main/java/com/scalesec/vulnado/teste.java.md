# Documentation for `ContractManager`

## Overview
The `ContractManager` is a Java program designed to manage contract details by collecting user input, validating the data, and simulating the saving of contract information. It ensures that required fields are provided and validates date formats and logical constraints (e.g., expiration date cannot precede the start date).

---

## Features
- Collects contract details from the user, including:
  - Contract Name
  - Parties Involved
  - Start Date
  - Expiration Date
  - Optional Notes
- Validates:
  - Required fields (`Contract Name` and `Parties Involved`)
  - Date format (`YYYY-MM-DD`)
  - Logical constraints (e.g., expiration date must not be earlier than the start date)
- Simulates saving contract details by printing them to the console.

---

## Code Structure

### 1. **Input Collection**
The program uses a `Scanner` to collect user input for the following fields:
- **Contract Name**: A required string field.
- **Parties Involved**: A required string field, expected to be comma-separated.
- **Start Date**: A required date field in the format `YYYY-MM-DD`.
- **Expiration Date**: A required date field in the format `YYYY-MM-DD`, which must not precede the start date.
- **Notes**: An optional string field.

### 2. **Validation**
- **Date Validation**: 
  - Ensures the input follows the `YYYY-MM-DD` format using `LocalDate.parse()`.
  - Handles invalid date formats with a `DateTimeParseException`.
- **Logical Validation**:
  - Ensures the expiration date is not earlier than the start date.
- **Required Fields**:
  - Ensures `Contract Name` and `Parties Involved` are not empty.

### 3. **Output**
- If validation passes, the program prints the contract details to the console.
- If validation fails, appropriate error messages are displayed, and the program exits.

---

## Key Classes and Methods

| Class/Method         | Description                                                                 |
|-----------------------|-----------------------------------------------------------------------------|
| `Scanner`            | Used to read user input from the console.                                  |
| `LocalDate.parse()`   | Parses a string into a `LocalDate` object, ensuring the correct date format.|
| `DateTimeParseException` | Catches invalid date format errors.                                      |
| `System.out.println()` | Outputs messages and contract details to the console.                     |

---

## Insights
- **Error Handling**: The program uses a `while` loop to repeatedly prompt the user for valid input until the correct format is provided. This ensures robust error handling for date inputs.
- **User Experience**: The program provides clear and actionable error messages, improving usability.
- **Extensibility**: While the program currently simulates saving by printing to the console, it can be extended to save data to a database or file system.
- **Validation Logic**: The logical check for the expiration date ensures data integrity by preventing invalid contract timelines.

---

## Example Usage
### Input:
```
Enter Contract Name:
Service Agreement
Enter Parties Involved (comma-separated):
Company A, Company B
Enter Start Date (YYYY-MM-DD):
2023-10-01
Enter Expiration Date (YYYY-MM-DD):
2023-12-31
Enter Notes (optional):
This is a test contract.
```

### Output:
```
Contract Details Saved Successfully:
Contract Name: Service Agreement
Parties Involved: Company A, Company B
Start Date: 2023-10-01
Expiration Date: 2023-12-31
Notes: This is a test contract.
```

---

## Limitations
- The program does not persist data; it only simulates saving by printing to the console.
- It assumes the user will provide valid comma-separated values for `Parties Involved` without further validation.

---
