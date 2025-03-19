# Documentation: `Comment.java`

## Overview
The `Comment` class is part of the `com.scalesec.vulnado` package and provides functionality for managing comments in a database. It includes methods for creating, fetching, deleting, and committing comments to a PostgreSQL database. The class also defines the structure of a `Comment` object.

---

## Class Structure

### Attributes
| Attribute Name | Type       | Description                                      |
|----------------|------------|--------------------------------------------------|
| `id`           | `String`   | Unique identifier for the comment.              |
| `username`     | `String`   | Username of the user who created the comment.   |
| `body`         | `String`   | Content of the comment.                         |
| `created_on`   | `Timestamp`| Timestamp indicating when the comment was created. |

### Constructor
```java
public Comment(String id, String username, String body, Timestamp created_on)
```
- Initializes a `Comment` object with the provided attributes.

---

## Methods

### `create(String username, String body)`
Creates a new `Comment` object and saves it to the database.

#### Parameters:
| Name       | Type     | Description                          |
|------------|----------|--------------------------------------|
| `username` | `String` | Username of the comment's creator.  |
| `body`     | `String` | Content of the comment.             |

#### Returns:
- `Comment`: The newly created `Comment` object.

#### Behavior:
1. Generates a unique ID for the comment using `UUID`.
2. Sets the current timestamp as the creation time.
3. Attempts to save the comment to the database using the `commit()` method.
4. Throws a `BadRequest` exception if saving fails.
5. Throws a `ServerError` exception if an unexpected error occurs.

---

### `fetch_all()`
Fetches all comments from the database.

#### Returns:
- `List<Comment>`: A list of all `Comment` objects retrieved from the database.

#### Behavior:
1. Executes a SQL query to fetch all rows from the `comments` table.
2. Maps each row to a `Comment` object.
3. Returns the list of `Comment` objects.

---

### `delete(String id)`
Deletes a comment from the database based on its ID.

#### Parameters:
| Name | Type     | Description                          |
|------|----------|--------------------------------------|
| `id` | `String` | Unique identifier of the comment.   |

#### Returns:
- `Boolean`: `true` if the deletion was successful, `false` otherwise.

#### Behavior:
1. Executes a SQL `DELETE` query with the provided ID.
2. Returns `true` if one row was affected, indicating successful deletion.
3. Returns `false` if an error occurs or no rows were affected.

---

### `commit()`
Private method that saves the current `Comment` object to the database.

#### Returns:
- `Boolean`: `true` if the insertion was successful, `false` otherwise.

#### Behavior:
1. Executes a SQL `INSERT` query to add the comment to the `comments` table.
2. Uses the object's attributes (`id`, `username`, `body`, `created_on`) as query parameters.

---

## Insights

### Database Interaction
- The class interacts with a PostgreSQL database using JDBC (`java.sql` package).
- It assumes the existence of a `Postgres.connection()` method to establish database connections.
- SQL queries are hardcoded, which may pose risks such as SQL injection if not properly sanitized.

### Error Handling
- Custom exceptions (`BadRequest` and `ServerError`) are used to handle specific error scenarios.
- Errors during database operations are logged using `e.printStackTrace()`.

### Potential Improvements
1. **Connection Management**: Ensure proper closing of database connections in all methods to avoid resource leaks.
2. **Validation**: Add input validation for `username` and `body` to prevent invalid data from being saved.
3. **Security**: Consider using parameterized queries or ORM frameworks to mitigate SQL injection risks.
4. **Scalability**: Fetching all comments (`fetch_all`) may not scale well for large datasets. Implement pagination or filtering.

### Dependencies
- Relies on a `Postgres.connection()` method for database connectivity.
- Assumes the existence of `BadRequest` and `ServerError` exception classes.

### Data Structure
The `Comment` class is both a data structure and a program. It defines the structure of a `Comment` object and includes logic for interacting with the database.
