# Entity management Service
A comprehensive service that can be integrated by clients to manage all aspects related to their entities.

[![license](https://img.shields.io/badge/license-MIT-green)](https://github.com/nehCG/ems/blob/main/LICENSE)
[![Build Status](https://github.com/nehCG/ems/actions/workflows/build.yml/badge.svg)](https://github.com/nehCG/ems/actions)
[![](https://img.shields.io/github/issues/nehCG/ems)](https://github.com/nehCG/ems/issues)
[![codecov](https://codecov.io/gh/nehCG/ems/branch/main/graph/badge.svg)](https://codecov.io/gh/nehCG/ems)

## Build and run the service using Docker

### Prerequisites

1. Ensure you have [Docker](https://www.docker.com/get-started) installed on your machine.
2. Ensure you have [Docker Compose](https://docs.docker.com/compose/install/) installed.

### 1. Clone the Repository

If you haven't already, clone the repository to your local machine.

### 2. Build the Docker Image

Navigate to the directory containing the `Dockerfile` and run:

```bash
docker build -t ems-image .
```

This will build a Docker image named `ems-image` from the `Dockerfile` in the current directory.

### 3. Run the Service using Docker

Navigate to the directory containing the `docker-compose.yml` and run:

```bash
docker-compose up
```

Then give it a couple of minutes because MySQL might take a little while to be ready, especially on the first run when it's initializing data.

### 4. Access the Service

After a few minutes, open your browser and navigate to:

[http://localhost:8080/ems](http://localhost:8080/ems)

You should now see the service is running!

### Troubleshooting

- If you encounter port conflicts (e.g., port `8080` is already in use), either stop the service using the port or bind the application to a different port when running the Docker container.
- Ensure your Docker daemon is running before executing Docker commands.
- Check application logs in the Docker container for any issues related to the Spring Boot application.

## Test the service

### Run all unit tests

```bash
./mvnw test -Dcheckstyle.skip=true
```

If you want to generate a test coverage report, you can choose the following two commands：

#### Clean old build artifacts, run tests, and verify the package is correct.
```bash
./mvnw clean verify -Dcheckstyle.skip=true
```

#### Clean old build artifacts, run tests, create the package, and install it to the local Maven repository.

```bash
./mvnw clean install -Dcheckstyle.skip=true
```

## Style Checker

We use CheckStyle with Sun Checks

### Run Checkstyle

```bash
./mvnw checkstyle:check
```

# API Documentation
### User Management Entry Points

Base URL: `http://localhost:8080/ems/user`

#### POST `/login`

- Description: Authenticate a user.
- Request Params: 
  - `userName`(String): The username of the user.
  - `userPwd`(String): The password of the user.
- Response: `UserModel` object for valid credentials.
- Status Codes:
    - 200 OK: Authentication successful.
    - 400 BAD REQUEST: If `userName` or `userPwd` is empty, or if credentials are incorrect.
    - 300 CUSTOM CODE: Represents business logic errors.
- **Postman API tests**:
  - Login success: [View Screenshot](postman_API_tests/user/login/Login_success.png)
  - User does not exist: [View Screenshot](postman_API_tests/user/login/Login_user_dne.png)
  - Username is empty: [View Screenshot](postman_API_tests/user/login/Login_username_empty.png)
  - Incorrect password: [View Screenshot](postman_API_tests/user/login/Login_incorrect_pwd.png)
  - Empty password: [View Screenshot](postman_API_tests/user/login/Login_password_empty.png)


#### POST `/updatePwd`

- Description: Update the password of an authenticated user.
- Request Params: 
  - `userId`(Integer): The ID of the user.
  - `oldPassword`(String): The current password of the user.
  - `newPassword`(String): The new password for the user.
  - `repeatPassword`(String): Confirmation of the new password.
- Response: Success message for a valid request.
- Status Codes:
    - 200 OK: Password updated successfully.
    - 400 BAD REQUEST: If `userId` does not exist, `oldPassword` is incorrect, or new passwords do not match/are empty.
    - 300 CUSTOM CODE: Represents business logic errors.
- **Postman API tests**:
  - Reset password success: [View Screenshot](postman_API_tests/user/updatePwd/ResetPwd_success.png)
  - Record does not exist: [View Screenshot](postman_API_tests/user/updatePwd/ResetPwd_record_dne.png)
  - Original password is empty: [View Screenshot](postman_API_tests/user/updatePwd/ResetPwd_orgPwd_empty.png)
  - Incorrect password: [View Screenshot](postman_API_tests/user/updatePwd/ResetPwd_incorrect_pwd.png)
  - New password is empty: [View Screenshot](postman_API_tests/user/updatePwd/ResetPwd_newPwd_empty.png)
  - Repeated password is empty: [View Screenshot](postman_API_tests/user/updatePwd/ResetPwd_repeatPwd_empty.png)
  - New password and Repeated password is inconsistent: [View Screenshot](postman_API_tests/user/updatePwd/ResetPwd_inconsistent.png)

#### POST `/add`

- Description: Register a new user.
- Request Params:
  - `userName`(String): The username of the user.
  - `email`(String): The email of the user.
  - `phone`(String): The phone number of the user.
- Request Body: `User` object.
- Response: Success message for a valid request.
- Status Codes:
    - 200 OK: User added successfully.
    - 400 BAD REQUEST: If input parameters are invalid or missing, or if the username already exists.
    - 300 CUSTOM CODE: Represents business logic errors.
- **Postman API tests**:
  - Add user success: [View Screenshot](postman_API_tests/user/add/AddUser_success.png)
  - Username is empty: [View Screenshot](postman_API_tests/user/add/AddUser_username_empty.png)
  - Email is empty: [View Screenshot](postman_API_tests/user/add/AddUser_email_empty.png)
  - Phone is empty: [View Screenshot](postman_API_tests/user/add/AddUser_phone_empty.png)

#### POST `/update`

- Description: Update an existing user's information.
- Request Params:
  - `id`(Integer): The ID of the user.
  - `userName`(String): The username of the user.
  - `email`(String): The email of the user.
  - `phone`(String): The phone number of the user.
- Request Body: `User` object.
- Response: Success message for a valid request.
- Status Codes:
    - 200 OK: User updated successfully.
    - 400 BAD REQUEST: If the `User` object is invalid, the user does not exist, or if the updated username already exists.
    - 300 CUSTOM CODE: Represents business logic errors.
- **Postman API tests**:
  - Update records success: [View Screenshot](postman_API_tests/user/update/UpdateUser_success.png)
  - Update user does not exist: [View Screenshot](postman_API_tests/user/update/UpdateUser_user_dne.png)
  - Username is empty: [View Screenshot](postman_API_tests/user/update/UpdateUser_username_empty.png)
  - Username already exists: [View Screenshot](postman_API_tests/user/update/UpdateUser_username_exists.png)
  - Email is empty: [View Screenshot](postman_API_tests/user/update/UpdateUser_email_empty.png)
  - Phone is empty: [View Screenshot](postman_API_tests/user/update/UpdateUser_phone_empty.png)

#### POST `/delete`

- Description: Delete one or more users.
- Request Params:
  - `id`(Integer): The ID of the user.
- Request Body: Array of user `ids`.
- Response: Success message for a valid request.
- Status Codes:
    - 200 OK: Users deleted successfully.
    - 400 BAD REQUEST: If the `ids` array is empty or if any of the `ids` do not correspond to existing users.
    - 300 CUSTOM CODE: Represents business logic errors.
- **Postman API tests**:
  - Delete users success: [View Screenshot](postman_API_tests/user/delete/DeleteUser_success.png)
  - Delete user does not exist: [View Screenshot](postman_API_tests/user/delete/DeleteUser_records_dne.png)
  - Delete user fail: [View Screenshot](postman_API_tests/user/delete/DeleteUser_failed.png)
  - User deleted proof: [View Screenshot](postman_API_tests/user/delete/DeleteUser_proof.png)

#### GET `/list`

- Description: Retrieve a list of users based on provided parameters.
- Request Params:
  - `page` (Integer): The page number. *Default: 1*
  - `pageSize` (Integer): The number of users per page. *Default: 10*
  - `username` (String): The username to filter by. *Optional*
  - `email` (String): The email address to filter by. *Optional*
- Response: Map with user information.
- Status Codes:
    - 200 OK: Request successful.
- **Postman API tests**:
  - Get all users success: [View Screenshot](postman_API_tests/user/list/Get_all_user.png)

### Shared Space Entry Points

Base URL: `http://localhost:8080/ems/announcement`

#### POST `/post`

- Description: Add a new shared data entry.
- Request Body: `SharedDataModel` object, with non-null `subject` and `content`.
- Response: "Shared data added successfully!" for valid input.
- Status Codes:
    - 200 OK: Successfully added.
    - 400 BAD REQUEST: If the `SharedDataModel` object is null, or `subject` or `content` is null/empty.
- **Postman API tests**:
  - Add shared data without content: [View Screenshot](postman_API_tests/announcement/post/Ann_content_empty.png)
  - Add shared data without subject: [View Screenshot](postman_API_tests/announcement/post/Ann_subject_empty.png)
  - Successfully add shared data: [View Screenshot](postman_API_tests/announcement/post/Ann_success.png)
  - Add shared data without uid: [View Screenshot](postman_API_tests/announcement/post/Ann_uid_null.png)

#### GET `/{id}`

- Description: Retrieve a specific shared data entry by its ID.
- Path Variable: `id` - The ID of the shared data entry.
- Response: `SharedDataModel` object for valid ID.
- Status Codes:
    - 200 OK: Successfully retrieved.
    - 400 BAD REQUEST: If `id` does not exist.
- **Postman API tests**:
  - Get shared data success: [View Screenshot](postman_API_tests/announcement/{id}/Get_ann_by_id.png)
  - Get not exist shared data: [View Screenshot](postman_API_tests/announcement/{id}/Get_by_id_dne.png)

#### GET `/all`

- Description: Retrieve all shared data entry in the database.
- Response: Map with `SharedDataModel` objects.
- Status Codes:
  - 200 OK: Successfully retrieved.
- **Postman API tests**:
  - Get all shared data success: [View Screenshot](postman_API_tests/announcement/all/Get_all_ann.png)

#### PUT `/update/{id}`

- Description: Update a specific shared data entry by its ID.
- Path Variable: `id` - The ID of the shared data entry.
- Request Body: `SharedDataModel` object, with non-null `uid`, `subject` and `content`.
- Response: "Shared data updated successfully!" for valid input and ID.
- Status Codes:
    - 200 OK: Successfully updated.
    - 400 BAD REQUEST: If `id` does not exist, or the `SharedDataModel` object is null, or `uid` is null, or `subject` or `content` is null/empty.
- **Postman API tests**:
  - Update with valid ID, userID, subject, and contents: [View Screenshot](postman_API_tests/announcement/update/Update_ann_success.png)
  - Proof of update subject and contents success: [View Screenshot](postman_API_tests/announcement/update/Update_ann_proof.png)
  - Update with valid ID, userID, and subject: [View Screenshot](postman_API_tests/announcement/update/Update_ann_subject_proof.png)
  - Proof of update subject success: [View Screenshot](postman_API_tests/announcement/update/Update_ann_subject_proof.png)
  - Update with valid ID, but no request body: [View Screenshot](postman_API_tests/announcement/update/Update_ann_null.png)
  - Update with valid ID and subject, but no userID: [View Screenshot](postman_API_tests/announcement/update/Update_ann_uid_null.png)

#### DELETE `/delete/{id}`

- Description: Delete a specific shared data entry by its ID.
- Path Variable: `id` - The ID of the shared data entry.
- Response: "Shared data deleted successfully!" for valid ID.
- Status Codes:
    - 204 NO CONTENT: Successfully deleted.
    - 400 BAD REQUEST: If `id` does not exist.
- **Postman API tests**:
  - Delete with valid ID: [View Screenshot](postman_API_tests/announcement/delete/Delete_ann_by_id_success.png)
  - Proof of delete with valid ID success: [View Screenshot](postman_API_tests/announcement/delete/Delete_ann_proof.png)