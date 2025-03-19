# Documentation for `LoginController.java`

## Overview
The `LoginController` class is a RESTful controller implemented using the Spring Boot framework. It provides an endpoint for user authentication by validating login credentials and returning a token upon successful authentication. The controller also handles unauthorized access by throwing a custom exception.

---

## Components

### 1. **`LoginController` Class**
The main controller class that handles the `/login` endpoint.

#### Key Features:
- **Endpoint**: `/login`
- **HTTP Method**: `POST`
- **Consumes**: `application/json`
- **Produces**: `application/json`
- **Cross-Origin Resource Sharing (CORS)**: Enabled for all origins (`*`).

#### Dependencies:
- **`@Value("${app.secret}")`**: Injects the application secret from the configuration file (e.g., `application.properties`).

#### Logic:
1. Accepts a `LoginRequest` object containing `username` and `password`.
2. Fetches the user details using the `User.fetch()` method.
3. Validates the password by comparing the hashed input password with the stored hashed password.
4. If valid, generates a token using the `User.token()` method and returns it in a `LoginResponse` object.
5. If invalid, throws an `Unauthorized` exception.

---

### 2. **`LoginRequest` Class**
A data structure representing the login request payload.

| Field Name | Type   | Description                  |
|------------|--------|------------------------------|
| `username` | String | The username of the user.    |
| `password` | String | The password of the user.    |

#### Characteristics:
- Implements `Serializable` for potential serialization needs.

---

### 3. **`LoginResponse` Class**
A data structure representing the login response payload.

| Field Name | Type   | Description                  |
|------------|--------|------------------------------|
| `token`    | String | The authentication token.    |

#### Characteristics:
- Implements `Serializable` for potential serialization needs.
- Constructor accepts a `String` message to initialize the `token` field.

---

### 4. **`Unauthorized` Class**
A custom exception class used to handle unauthorized access.

| Field Name | Type   | Description                  |
|------------|--------|------------------------------|
| `exception`| String | The exception message.       |

#### Characteristics:
- Annotated with `@ResponseStatus(HttpStatus.UNAUTHORIZED)` to return a 401 HTTP status code when thrown.
- Extends `RuntimeException`.

---

## Insights

### Security Considerations
1. **Password Hashing**: The password is hashed using `Postgres.md5()`. Ensure that the hashing mechanism is secure and up-to-date with modern cryptographic standards.
2. **Token Generation**: The `User.token(secret)` method is used to generate tokens. Ensure that the token generation logic is secure and resistant to tampering.
3. **CORS Policy**: The `@CrossOrigin(origins = "*")` annotation allows requests from all origins. This may expose the endpoint to potential security risks. Consider restricting origins to trusted domains.

### Error Handling
- Unauthorized access is handled gracefully by throwing the `Unauthorized` exception, which returns a 401 status code with a custom message.

### Dependency Injection
- The `@Value("${app.secret}")` annotation ensures that the application secret is injected from the configuration, promoting flexibility and security.

### Serialization
- Both `LoginRequest` and `LoginResponse` implement `Serializable`, which is useful for scenarios where objects need to be serialized (e.g., caching, messaging).

### Extensibility
- The design allows for easy extension, such as adding more fields to the request/response or introducing additional validation logic.

---

## Endpoint Summary

| Endpoint | HTTP Method | Request Body       | Response Body       | Status Codes       |
|----------|-------------|--------------------|---------------------|--------------------|
| `/login` | POST        | `LoginRequest`     | `LoginResponse`     | 200 (Success), 401 (Unauthorized) |
