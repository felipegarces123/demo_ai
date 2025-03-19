# Documentation: `CommentsController.java`

## Overview
The `CommentsController` class is a RESTful controller implemented using Spring Boot. It provides endpoints for managing comments, including fetching, creating, and deleting comments. The controller also includes mechanisms for authentication and error handling.

---

## Class: `CommentsController`

### Description
The `CommentsController` class is annotated with `@RestController` and `@EnableAutoConfiguration`, making it a Spring Boot REST controller. It handles HTTP requests related to comments and includes authentication via a secret token.

### Fields
| Field Name | Type   | Description                                                                 |
|------------|--------|-----------------------------------------------------------------------------|
| `secret`   | String | A secret value injected from the application's configuration (`app.secret`). |

### Endpoints

#### 1. **GET `/comments`**
   - **Description**: Fetches all comments.
   - **Method**: `GET`
   - **Produces**: `application/json`
   - **Headers**:
     - `x-auth-token`: Authentication token required for authorization.
   - **Logic**:
     - Validates the `x-auth-token` using the `User.assertAuth` method.
     - Returns a list of all comments by calling `Comment.fetch_all()`.
   - **Cross-Origin**: Enabled for all origins (`@CrossOrigin(origins = "*")`).

#### 2. **POST `/comments`**
   - **Description**: Creates a new comment.
   - **Method**: `POST`
   - **Produces**: `application/json`
   - **Consumes**: `application/json`
   - **Headers**:
     - `x-auth-token`: Authentication token required for authorization.
   - **Request Body**:
     - `CommentRequest` object containing:
       - `username`: The username of the comment's author.
       - `body`: The content of the comment.
   - **Logic**:
     - Creates a new comment using `Comment.create(input.username, input.body)`.
   - **Cross-Origin**: Enabled for all origins (`@CrossOrigin(origins = "*")`).

#### 3. **DELETE `/comments/{id}`**
   - **Description**: Deletes a comment by its ID.
   - **Method**: `DELETE`
   - **Produces**: `application/json`
   - **Headers**:
     - `x-auth-token`: Authentication token required for authorization.
   - **Path Variables**:
     - `id`: The ID of the comment to be deleted.
   - **Logic**:
     - Deletes the comment using `Comment.delete(id)`.
   - **Cross-Origin**: Enabled for all origins (`@CrossOrigin(origins = "*")`).

---

## Class: `CommentRequest`

### Description
A data structure representing the request body for creating a comment. Implements `Serializable`.

### Fields
| Field Name | Type   | Description                          |
|------------|--------|--------------------------------------|
| `username` | String | The username of the comment's author. |
| `body`     | String | The content of the comment.          |

---

## Class: `BadRequest`

### Description
A custom exception class annotated with `@ResponseStatus(HttpStatus.BAD_REQUEST)`. It is used to represent client-side errors (HTTP 400).

### Constructor
| Parameter   | Type   | Description                     |
|-------------|--------|---------------------------------|
| `exception` | String | The error message for the exception. |

---

## Class: `ServerError`

### Description
A custom exception class annotated with `@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)`. It is used to represent server-side errors (HTTP 500).

### Constructor
| Parameter   | Type   | Description                     |
|-------------|--------|---------------------------------|
| `exception` | String | The error message for the exception. |

---

## Insights

1. **Authentication**:
   - The controller uses a secret token (`x-auth-token`) for authentication. The `User.assertAuth` method is responsible for validating the token.

2. **Cross-Origin Resource Sharing (CORS)**:
   - All endpoints have CORS enabled for all origins (`@CrossOrigin(origins = "*")`), which may pose security risks if not properly configured.

3. **Error Handling**:
   - Custom exceptions (`BadRequest` and `ServerError`) are defined to handle specific HTTP error statuses. This improves clarity and debugging during runtime.

4. **Data Structure**:
   - The `CommentRequest` class is a simple data structure for encapsulating the request body when creating comments.

5. **Dependency Injection**:
   - The `@Value` annotation is used to inject the `secret` field from the application's configuration, ensuring flexibility and separation of concerns.

6. **Potential Security Concerns**:
   - The use of `@CrossOrigin(origins = "*")` without restrictions may expose the application to cross-origin attacks.
   - The `x-auth-token` mechanism should be reviewed to ensure secure token management and validation.

7. **Scalability**:
   - The `Comment.fetch_all()` and `Comment.delete(id)` methods suggest reliance on static methods for data operations. Consider using a repository or service layer for better scalability and maintainability.
