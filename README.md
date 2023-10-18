# COMS4156-MVC-masters
Entity management system is a comprehensive service that can be integrated by clients to manage all aspects related to their entities.

## Operational Entry Points
### User Management Entry Points

Base URL: `http://localhost:8080/ems/user`

#### POST `/login`

- Description: Authenticate a user.
- Request Params: `userName`, `userPwd`
- Response: `UserModel` object for valid credentials.
- Status Codes:
    - 200 OK: Authentication successful.
    - 400 BAD REQUEST: If `userName` or `userPwd` is empty, or if credentials are incorrect.

#### POST `/updatePwd`

- Description: Update the password of an authenticated user.
- Request Params: `userId`, `oldPassword`, `newPassword`, `repeatPassword`
- Response: Success message for a valid request.
- Status Codes:
    - 200 OK: Password updated successfully.
    - 400 BAD REQUEST: If `userId` does not exist, `oldPassword` is incorrect, or new passwords do not match/are empty.

#### POST `/add`

- Description: Register a new user.
- Request Body: `User` object.
- Response: Success message for a valid request.
- Status Codes:
    - 200 OK: User added successfully.
    - 400 BAD REQUEST: If input parameters are invalid or missing, or if the username already exists.

#### POST `/update`

- Description: Update an existing user's information.
- Request Body: `User` object.
- Response: Success message for a valid request.
- Status Codes:
    - 200 OK: User updated successfully.
    - 400 BAD REQUEST: If the `User` object is invalid, the user does not exist, or if the updated username/email/phone is invalid or already exists.

#### POST `/delete`

- Description: Delete one or more users.
- Request Body: Array of user `ids`.
- Response: Success message for a valid request.
- Status Codes:
    - 200 OK: Users deleted successfully.
    - 400 BAD REQUEST: If the `ids` array is empty or if any of the `ids` do not correspond to existing users.

#### GET `/list`

- Description: Retrieve a list of users based on provided parameters.
- Request Params: `UserQuery` object (define the expected fields).
- Response: Map with user information.
- Status Codes:
    - 200 OK: Request successful.

### Shared Space Entry Points

Base URL: `http://localhost:8080/ems/announcement`

#### POST `/post`

- Description: Add a new shared data entry.
- Request Body: `SharedDataModel` object, with non-null `subject` and `content`.
- Response: "Shared data added successfully!" for valid input.
- Status Codes:
    - 200 OK: Successfully added.
    - 400 BAD REQUEST: If the `SharedDataModel` object is null, or `subject` or `content` is null/empty.

#### GET `/{id}`

- Description: Retrieve a specific shared data entry by its ID.
- Path Variable: `id` - The ID of the shared data entry.
- Response: `SharedDataModel` object for valid ID.
- Status Codes:
    - 200 OK: Successfully retrieved.
    - 400 BAD REQUEST: If `id` does not exist.

#### GET `/all`

- Description: Retrieve all shared data entries.
- Response: List of `SharedDataModel` objects.
- Status Code: 200 OK

#### PUT `/update/{id}`

- Description: Update a specific shared data entry by its ID.
- Path Variable: `id` - The ID of the shared data entry.
- Request Body: `SharedDataModel` object, with non-null `subject` and `content`.
- Response: "Shared data updated successfully!" for valid input and ID.
- Status Codes:
    - 200 OK: Successfully updated.
    - 400 BAD REQUEST: If `id` does not exist, or the `SharedDataModel` object is null, or `subject` or `content` is null/empty.

#### DELETE `/delete/{id}`

- Description: Delete a specific shared data entry by its ID.
- Path Variable: `id` - The ID of the shared data entry.
- Response: "Shared data deleted successfully!" for valid ID.
- Status Codes:
    - 204 NO CONTENT: Successfully deleted.
    - 400 BAD REQUEST: If `id` does not exist.

## Instructions
_To include instructions explaining how to build, run and test your service_