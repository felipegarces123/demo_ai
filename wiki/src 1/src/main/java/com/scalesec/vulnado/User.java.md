# Documentation: `User` Class

## Overview

The `User` class is part of the `com.scalesec.vulnado` package and represents a user entity in the system. It provides functionality for user authentication, token generation, and database interaction to fetch user details. The class includes methods for creating JSON Web Tokens (JWTs), validating tokens, and retrieving user data from a PostgreSQL database.

---

## Class: `User`

### Fields

| Field Name       | Type     | Description                                      |
|------------------|----------|--------------------------------------------------|
| `id`             | `String` | The unique identifier for the user.             |
| `username`       | `String` | The username of the user.                       |
| `hashedPassword` | `String` | The hashed password of the user.                |

---

### Constructor

#### `User(String id, String username, String hashedPassword)`

Initializes a new `User` object with the provided `id`, `username`, and `hashedPassword`.

| Parameter         | Type     | Description                                      |
|-------------------|----------|--------------------------------------------------|
| `id`              | `String` | The unique identifier for the user.             |
| `username`        | `String` | The username of the user.                       |
| `hashedPassword`  | `String` | The hashed password of the user.                |

---

### Methods

#### `String token(String secret)`

Generates a JSON Web Token (JWT) for the user using the provided secret key.

| Parameter | Type     | Description                                      |
|-----------|----------|--------------------------------------------------|
| `secret`  | `String` | The secret key used to sign the JWT.             |

**Returns:**  
A `String` representing the generated JWT.

---

#### `static void assertAuth(String secret, String token)`

Validates a given JWT using the provided secret key. If the token is invalid, an `Unauthorized` exception is thrown.

| Parameter | Type     | Description                                      |
|-----------|----------|--------------------------------------------------|
| `secret`  | `String` | The secret key used to validate the JWT.         |
| `token`   | `String` | The JWT to be validated.                         |

**Throws:**  
- `Unauthorized` if the token is invalid.

---

#### `static User fetch(String un)`

Fetches a user from the database based on the provided username. The method queries the `users` table and returns a `User` object if a match is found.

| Parameter | Type     | Description                                      |
|-----------|----------|--------------------------------------------------|
| `un`      | `String` | The username to search for in the database.      |

**Returns:**  
A `User` object if the user is found, otherwise `null`.

**Database Query:**  
```sql
SELECT * FROM users WHERE username = '<username>' LIMIT 1;
```

---

## Insights

### Security Concerns
1. **SQL Injection Vulnerability**:  
   The `fetch` method constructs SQL queries using string concatenation, making it vulnerable to SQL injection attacks. It is recommended to use prepared statements to mitigate this risk.

2. **Weak Secret Management**:  
   The `token` and `assertAuth` methods rely on a secret key passed as a string. Ensure that the secret is securely stored and managed (e.g., using environment variables or a secrets management tool).

3. **Exception Handling**:  
   The `assertAuth` method catches exceptions and rethrows them as `Unauthorized`. However, sensitive information from the exception stack trace may be exposed. Avoid printing stack traces in production environments.

### Database Connection Management
- The `fetch` method does not use a connection pool, which may lead to performance issues in high-concurrency scenarios. Consider using a connection pooling library like HikariCP.

### JWT Implementation
- The `token` method uses the `io.jsonwebtoken` library to generate JWTs. Ensure that the secret key length meets the requirements of the HMAC-SHA algorithm.

### Code Design
- The `fetch` method directly interacts with the database, which may violate separation of concerns. Consider abstracting database operations into a dedicated repository or DAO (Data Access Object) layer.

### Error Handling
- The `fetch` method logs errors to the console but does not propagate them. This may make debugging and error tracking more difficult. Consider using a logging framework like SLF4J or Log4j for better logging practices.

---

## Dependencies

The `User` class relies on the following libraries and classes:

| Dependency                  | Description                                      |
|-----------------------------|--------------------------------------------------|
| `java.sql.Connection`       | For establishing a connection to the database.  |
| `java.sql.Statement`        | For executing SQL queries.                      |
| `java.sql.ResultSet`        | For processing query results.                   |
| `io.jsonwebtoken.*`         | For generating and validating JWTs.             |
| `javax.crypto.SecretKey`    | For cryptographic operations.                   |
| `Postgres.connection()`     | A custom method for obtaining a database connection. |

---

## Potential Enhancements

1. **Use Prepared Statements**: Replace string concatenation in SQL queries with prepared statements to prevent SQL injection.
2. **Centralized Error Handling**: Implement a centralized error-handling mechanism to improve maintainability and debugging.
3. **Connection Pooling**: Introduce a connection pool to optimize database interactions.
4. **Environment-Specific Configurations**: Use environment variables or configuration files to manage secrets and database connection details securely.
