# Documentation: `Postgres.java`

## Overview

The `Postgres` class provides utility methods for interacting with a PostgreSQL database. It includes functionality for establishing a database connection, setting up the database schema, inserting seed data, and hashing passwords using the MD5 algorithm. This class is designed to initialize and manage a database for a user and comment system.

---

## Features

### 1. Database Connection
The `connection()` method establishes a connection to a PostgreSQL database using credentials and connection details provided via environment variables.

### 2. Database Setup
The `setup()` method:
- Creates the necessary database schema (tables for users and comments).
- Cleans up any existing data in the tables.
- Inserts seed data for users and comments.

### 3. Password Hashing
The `md5(String input)` method generates an MD5 hash for a given input string. This is used to hash user passwords before storing them in the database.

### 4. Data Insertion
- `insertUser(String username, String password)`: Inserts a new user into the `users` table with a hashed password.
- `insertComment(String username, String body)`: Inserts a new comment into the `comments` table.

---

## Environment Variables

The following environment variables are required for the database connection:

| Variable Name | Description                          |
|---------------|--------------------------------------|
| `PGHOST`      | Hostname of the PostgreSQL server.   |
| `PGDATABASE`  | Name of the PostgreSQL database.     |
| `PGUSER`      | Username for the database connection.|
| `PGPASSWORD`  | Password for the database connection.|

---

## Database Schema

The `setup()` method creates the following tables:

### 1. `users` Table
| Column Name   | Data Type      | Constraints                     |
|---------------|----------------|----------------------------------|
| `user_id`     | `VARCHAR(36)`  | Primary Key                     |
| `username`    | `VARCHAR(50)`  | Unique, Not Null                |
| `password`    | `VARCHAR(50)`  | Not Null                        |
| `created_on`  | `TIMESTAMP`    | Not Null                        |
| `last_login`  | `TIMESTAMP`    | Nullable                        |

### 2. `comments` Table
| Column Name   | Data Type      | Constraints                     |
|---------------|----------------|----------------------------------|
| `id`          | `VARCHAR(36)`  | Primary Key                     |
| `username`    | `VARCHAR(36)`  | Foreign Key (not enforced here) |
| `body`        | `VARCHAR(500)` | No Constraints                  |
| `created_on`  | `TIMESTAMP`    | Not Null                        |

---

## Methods

### 1. `connection()`
- **Purpose**: Establishes a connection to the PostgreSQL database.
- **Returns**: `Connection` object.
- **Error Handling**: Prints stack trace and exits the program on failure.

### 2. `setup()`
- **Purpose**: Sets up the database schema, cleans up existing data, and inserts seed data.
- **Seed Data**:
  - Users: `admin`, `alice`, `bob`, `eve`, `rick`.
  - Comments: `"cool dog m8"` (by `rick`), `"OMG so cute!"` (by `alice`).

### 3. `md5(String input)`
- **Purpose**: Generates an MD5 hash for the given input string.
- **Returns**: MD5 hash as a `String`.
- **Error Handling**: Throws a `RuntimeException` if the MD5 algorithm is unavailable.

### 4. `insertUser(String username, String password)`
- **Purpose**: Inserts a new user into the `users` table with a hashed password.
- **Parameters**:
  - `username`: The username of the user.
  - `password`: The plaintext password of the user.

### 5. `insertComment(String username, String body)`
- **Purpose**: Inserts a new comment into the `comments` table.
- **Parameters**:
  - `username`: The username of the commenter.
  - `body`: The content of the comment.

---

## Insights

1. **Security Concerns**:
   - The use of MD5 for password hashing is insecure and outdated. Consider using a stronger hashing algorithm like bcrypt, Argon2, or PBKDF2.
   - Storing passwords as simple hashes without salting increases vulnerability to rainbow table attacks.

2. **Error Handling**:
   - The `connection()` method exits the program on failure, which may not be ideal for production systems. Consider throwing exceptions or implementing a retry mechanism.
   - The `insertUser` and `insertComment` methods catch exceptions but do not propagate them, which may lead to silent failures.

3. **Database Design**:
   - The `username` column in the `comments` table is not explicitly defined as a foreign key referencing the `users` table. Adding this constraint would improve data integrity.

4. **Hardcoded Seed Data**:
   - The seed data includes plaintext passwords, which is not recommended for production environments. Consider externalizing this data or using environment variables.

5. **Scalability**:
   - The current implementation is suitable for small-scale applications. For larger systems, consider using connection pooling and optimizing database queries.

6. **UUID Usage**:
   - The use of UUIDs for primary keys ensures uniqueness and avoids potential conflicts in distributed systems.
