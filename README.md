# Entity Management Service
A comprehensive service that can be integrated by clients to manage all aspects related to their entities.

[![license](https://img.shields.io/badge/license-MIT-green)](https://github.com/nehCG/ems/blob/main/LICENSE)
[![Build Status](https://github.com/nehCG/ems/actions/workflows/build.yml/badge.svg)](https://github.com/nehCG/ems/actions)
[![](https://img.shields.io/github/issues/nehCG/ems)](https://github.com/nehCG/ems/issues)
[![codecov](https://codecov.io/gh/nehCG/ems/branch/main/graph/badge.svg)](https://codecov.io/gh/nehCG/ems)

## Table of Contents
- [Build and Run](#build-and-run-the-service-using-docker)
  - [Prerequisites](#prerequisites)
  - [1. Clone the Repo](#1-clone-the-repository)
  - [2. Build the Docker Image](#2-build-the-docker-image)
  - [3. Run the Service using Docker](#3-run-the-service-using-docker)
  - [4. Access the Service](#4-access-the-service)
  - [Troubleshooting](#troubleshooting)
- [Unit Tests](#unit-tests-of-the-service)
- [Integration Tests](#integration-tests-of-the-service)
- [System Tests](#system-tests-corresponding-to-api)
  - [API Entry Point Test](#system-level-tests-of-every-api-entry-point)
  - [Multiple Clients Test](#system-level-tests-of-multiple-clients)
- [End-to-End Testing Checklist](#end-to-end-testing-checklist)
- [Testing for Multiple Simultaneous Requests](#testing-for-multiple-simultaneous-requests)
- [Style Checker](#style-checker)
- [Bug Finder](#bug-finder)
- [API Documentation](#api-documentation)
- [Persistent Data Storage](#persistent-data-storage-of-our-service)
- [Change In API Implementation From Proposal](#change-in-api-implementation-from-proposal)
- [About Our Client App](#about-our-client-app)

## Build and Run the Service using Docker

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

Then, the service will run on [`http://localhost:8080/ems`](http://localhost:8080/ems).

### Troubleshooting

- If you encounter port conflicts (e.g., port `8080` is already in use), either stop the service using the port or bind the application to a different port when running the Docker container.
- Ensure your Docker daemon is running before executing Docker commands.
- Check application logs in the Docker container for any issues related to the Spring Boot application.

## Unit Tests of the Service

### Run all unit tests

```bash
./mvnw test -Dcheckstyle.skip=true -Dspotbugs.skip=true
```

If you want to generate a test coverage report, you can choose the following two commands：

#### Clean old build artifacts, run tests, and verify the package is correct.
```bash
./mvnw clean verify -Dcheckstyle.skip=true -Dspotbugs.skip=true
```

#### Clean old build artifacts, run tests, create the package, and install it to the local Maven repository.

```bash
./mvnw clean install -Dcheckstyle.skip=true -Dspotbugs.skip=true
```

Our unit tests have 98.6% code coverage. [Codecov report](https://app.codecov.io/gh/nehCG/ems)

## Integration Tests of the Service
We employed the Big Bang Integration Testing approach, where the integration of multiple units are tested 
simultaneously. Then, the entire system is tested as a whole. 

### Internal Integration Test
In this phase, we focused on two key integration. The first one is the interaction between Controller Layer and Service Layer, which involved mocking the HTTP request and allow the service layer to process. The second one is the interaction between Service Layer and Repository Layer, which involved mocking the database. 
After these targeted tests, we proceeded to the External Integration Test, where we examined the entire service through RESTful API testing.

### External Integration Test
Our services has two external components, which are RESTFul API and the MySQL database. Thus, we tested the 
interaction between the Mapper files and the external MySQL database. Next, we used @RestTemplate
to call each API endpoints, where the service is tested as a whole. 

Both Internal and External Integration Tests are located in src/test/java, which will be run automatically during CI. The folder containing external integration tests is excluded for the 'mvn test' build during CI.  

## System Tests Corresponding to API
### System-level Tests of every API Entry Point
Please see details on below [API Documentation](#api-documentation) section.

### System-level Tests of Multiple Clients

We use the Runner in Postman to mock multiple clients are sending requests simultaneously.

We performed several API functional and performance tests that may face multiple clients requesting and high concurrency. You can check details [here](postman_API_tests/multiple_clients_testing).

**Login**: `http://localhost:8080/ems/user/login`
- Functional test (1000 iterations, 0ms delays)
  - Setting: [View Screenshot](postman_API_tests/multiple_clients_testing/login/functional_test/Login_functional_test_setting.png)
  - Result: [View Screenshot](postman_API_tests/multiple_clients_testing/login/functional_test/Login_functional_test_result.png)
- Performance test (100 virtual clients, 1 min test duration)
  - Setting: [View Screenshot](postman_API_tests/multiple_clients_testing/login/performance_test/Login_performance_test_setting.png)
  - Result: [View Screenshot](postman_API_tests/multiple_clients_testing/login/performance_test/Login_performance_test_result.png)

**Get all users**: `http://localhost:8080/ems/user/all`
- Functional test (1000 iterations, 0ms delays)
  - Setting: [View Screenshot](postman_API_tests/multiple_clients_testing/query_users/functional_test/GetAllUsers_functional_test_setting.png)
  - Result: [View Screenshot](postman_API_tests/multiple_clients_testing/query_users/functional_test/GetAllUsers_functional_test_result.png)
- Performance test (100 virtual clients, 1 min test duration)
  - Setting: [View Screenshot](postman_API_tests/multiple_clients_testing/query_users/performance_test/GetAllUsers_performance_test_setting.png)
  - Result: [View Screenshot](postman_API_tests/multiple_clients_testing/query_users/performance_test/GetAllUsers_performance_test_result.png)

**Get all announcements**: `http://localhost:8080/ems/announcements/all`
- Functional test (1000 iterations, 0ms delays)
  - Setting: [View Screenshot](postman_API_tests/multiple_clients_testing/query_anns/functional_test/GetAllAnns_functional_test_setting.png)
  - Result: [View Screenshot](postman_API_tests/multiple_clients_testing/query_anns/functional_test/GetAllAnns_functional_test_result.png)
- Performance test (100 virtual clients, 1 min test duration)
  - Setting: [View Screenshot](postman_API_tests/multiple_clients_testing/query_anns/performance_test/GetAllAnns_performance_test_setting.png)
  - Result: [View Screenshot](postman_API_tests/multiple_clients_testing/query_anns/performance_test/GetAllAnns_performance_test_result.png)


## End-to-End Testing Checklist
Please see details in [E2E Testing Checklist](/E2E-Testing-Checklist.md)


## Testing for Multiple Simultaneous Requests

To ensure robust performance under high load, we used Apache JMeter for performance testing on key endpoints.

Our focus was on scenarios likely to experience heavy traffic:
- Concurrent User Logins: Tested the system's response to 100 simultaneous user logins
  [View Screenshot](JMeter_tests/Login 100 Concurrent Users.png)
- Bulk Announcement Posting: Assessed system stability with 100 announcements posted concurrently
  [View Screenshot](JMeter_tests/Add 100 Concurrent Announcements.png)

Alongside Apache JMeter tests, we conducted real-world scenario tests to validate our system's performance
and responsiveness:

- Manual Multi-Instance Testing: Simulated user activities by opening multiple browsers,
  logging in as different users, and performing actions like posting announcements and adjusting role assignments.

- Real-Time Responsiveness: Verified that changes made by one user (e.g., role adjustments) were immediately
  reflected for other users, ensuring real-time data consistency and dynamic interaction.

## Style Checker

We use CheckStyle with Sun Checks

### Run Checkstyle

```bash
./mvnw checkstyle:check
```

Our checkstyle results are clean. [Checkstyle report](reports/checkstyle_report.png)

## Bug Finder

We use SpotBugs Bug Detector

### Run SpotBugs

```bash
./mvnw spotbugs:check
```

Our Spotbugs results are clean. [SpotBugs report](reports/SpotBugs_report.png)

To generate report, run ```./mvnw clean compile site```

## API Documentation

### FrontEnd Entry Points

Base URL: http://localhost:8080/ems

#### GET: `/index`
- Description: Display the login page.
  - Frontend login page: [View Screenshot](FrontEndScreenShots/loginPage.png)

#### GET: `/main`
- Description: Display the main page of the service.
  - Frontend login page: [View Screenshot](FrontEndScreenShots/mainPage.png)

#### GET: `/welcome`
- Description: Display the welcome pag.
  - Frontend login page: [View Screenshot](FrontEndScreenShots/welcomePage.png)

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
  - New password is same as old password: [View Screenshot](postman_API_tests/user/updatePwd/ResetPwd_newPwd_same_oldPwd.png)
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

#### GET `/index`

- Description: Direct to the main user page.
- Response: A string representing the path the main user management page.
- Status Codes:
  - 200 OK: Request successful.

#### GET `/toPasswordPage`

- Description: Direct to the password management page.
- Response: A string representing the path to the password management page.
- Status Codes:
  - 200 OK: Request successful.

#### GET `/toAddOrUpdateUserPage`

- Description: Direct to the page for adding or updating a user.
- Request Params:
  - `roleId` (Integer): The ID of the user to be updated, or null if adding a new user. *Optional*
- Response: A string representing the path to the view for adding a new user or updating an existing one.
- Status Codes:
  - 200 OK: Request successful.

### Role Management Entry Points

Base URL: `http://localhost:8080/ems/role`

#### GET `/queryAllRoles`

- Description: Retrieve all roles associated with a given user ID.
- Request Params:
  - `roleId` (Integer): The role ID used for querying roles. *Default: 1*
- Response: A list of maps, each representing a role and its information.
- Status Codes:
  - 200 OK: Request successful.

#### GET `/list`

- Description: Retrieve a list of roles based on provided parameters.
- Request Params:
  - `roleName` (Integer): The name of the role being queried. *Default: 1*
- Response: Map with user information.
- Status Codes:
  - 200 OK: Request successful.

#### POST `/add`

- Description: Add a new role into the system.
- Request Params:
  - `roleName`(String): The name of the role.
- Request Body: `Role` object.
- Response: Success message for a valid request.
- Status Codes:
  - 200 OK: Role added successfully.
  - 400 BAD REQUEST: If input parameters are invalid or missing, or if the rolename already exists.
  - 300 CUSTOM CODE: Represents business logic errors.

#### POST `/update`

- Description: Update an existing role information in the system.
- Request Params:
  - `roleId`(Integer): The ID of the role.
  - `roleName`(String): The name of the role.
- Request Body: `Role` object.
- Response: Success message for a valid request.
- Status Codes:
  - 200 OK: Role updated successfully.
  - 400 BAD REQUEST: If the `Role` object is invalid, the roleID does not exist, or if the updated rolename already exists.
  - 300 CUSTOM CODE: Represents business logic errors.

#### POST `/delete`

- Description: Delete a role from the system.
- Request Params:
  - `roleId`(Integer): The ID of the role.
- Request Body: The role id.
- Response: Success message for a valid request.
- Status Codes:
  - 200 OK: Role deleted successfully.
  - 400 BAD REQUEST: If the `roleId` is empty or if the `roleId` does not correspond to existing users.
  - 300 CUSTOM CODE: Represents business logic errors.

#### POST `/addGrant`

- Description: Grant permissions to a specific role
- Request Params:
  - `roleId`(Integer): The ID of the role.
  - `mId`(Integer):  ModuleID that is to be granted to the role.
- Request Body: The roleId and Array of module `mId`s.
- Response: Success message for a valid request.
- Status Codes:
  - 200 OK: Role deleted successfully.
  - 400 BAD REQUEST: If the `roleId` is empty or if the `roleId` does not correspond to existing users.
  - 300 CUSTOM CODE: Represents business logic errors.

#### GET `/index`

- Description: Direct to the role index page.
- Response: A string representing the path to the role index view.
- Status Codes:
  - 200 OK: Request successful.

#### GET `/toAddOrUpdateRolePage`

- Description: Direct to a page for adding or updating a role.
- Request Params:
  - `roleId`(Integer): The ID of the role to edit, or null for adding a new role.
- Response: A string representing the path to the role index view.
- Status Codes:
  - 200 OK: Request successful.

### Module Operations Entry Points

Base URL: `http://localhost:8080/ems/module`

#### GET `/queryAllModules`

- Description: Retrieve all modules based on the given role ID.
- Request Params:
  - `roleId` (Integer): The role ID used for querying modules. *Default: 1*
- Response: List of TreeModel representing Module Information.
- Status Codes:
  - 200 OK: Request successful.

#### GET `/toAddGrantPage`

- Description: Directs the user to the page for adding grants to a role.
- Request Params:
  - `roleId` (Integer): The ID of the role to add grants to. *Default: 1*
- Response: The path to the "grant" view under the "role" directory.
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

#### GET `/index`

- Description: Direct to the shared space index page.
- Response: A string representing the path to the shared space index view.
- Status Codes:
  - 200 OK: Request successful.

## Persistent Data Storage of our Service

Based on the provided `docker-compose.yml`, the MySQL database container is set up to ensure data persistence
through Docker volumes. Specifically, the volumes directive under the mysql-db service maps db-data to `/var/lib/mysql`,
which is the default location where MySQL stores its data files. By doing so, any data changes made within the database
are stored in this Docker-managed volume, named `ems_db-data`. As a result, even if the MySQL container is stopped,
deleted, or recreated, the data remains intact and can be reattached to a new instance of the MySQL container.

This volume-driven approach guarantees the resilience and persistence of data across container lifecycle events,
ensuring that our database changes are consistently retained and not ephemeral.

In the course of our comprehensive System-level Testing for the Service, we rigorously executed all CRUD
(Create, Read, Update, Delete) operations utilizing Postman as our primary testing interface. For each individual operation,
there was a direct and verifiable reflection in the MySQL database. This consistent alignment between the API calls and
the resulting database modifications unequivocally confirms the robust interaction and integration of our service layer
with the persistent data layer. Our meticulous testing approach ensures that our application not only responds to API
requests as expected but also effectively manages the underlying data in a reliable and consistent manner.


## Change In API Implementation From Proposal

We decided to make the following changes to our API implementation, which are different from our proposal:

1. Add role management:
   Role management allows for the definition of roles, each with specific permissions. 
   This is crucial in an entity management system to control who can perform certain actions on entities.
   For data protection, different roles should have different levels of access to sensitive information. 
   Role management ensures that only authorized individuals can view or modify specific entities, contributing to data security.

2. Remove customizable workflow:
   Our entity management system focuses on handling and organizing data related to entities (e.g., information of the entities, roles) 
   and we decided not to add the complexity of managing intricate workflows.

3. Remove multi-language translation
   Once again, our entity management system prioritizes the core functionalities related to entities (e.g., CRUD of entities, roles).
   And there various multi-language translation API provided by major technology companies, 
   such as Google Cloud Translation API, Microsoft Translator API, and DeepL API.


## About Our Client App

Our client app is an application tailored for pre-K school internal management. 
It provides a centralized platform for administrators, teachers, and other school roles to collaborate and manage various aspects of the pre-K education experience.
Key Features:

- Announcement board: the announcement board uses CRUD APIs from /announcement in our service.
Pre-K school teachers and administrators can post announcements, which will be visible to all Pre-K school employees.
The announcement board serves as an effective notification platform.


- Role management: the authorization of each role can be customized.
This functionality uses /role/list, /role/toAddOrUpdateRolePage, /role/delete, and /module/toAddGrantPage.
The administrators or other authorized roles are able to manage the authorization of each role in the Pre-K school.
They are also able to create/delete/edit each role. 
This role management functionality helps to manage the scope of roles within Pre-K school.


- User management: users can be added, edited, or removed by authorized entities. 
The user management uses CRUD APIs from /user.
The administrators or other authorized roles are able to manage all employees within the Pre-K school.
This user management functionality provides the flexibility for adding new teachers or employees.


- User setting: users are able to update their password. 
This functionality uses /updatePwd from /user in our service
After login, individual can change the default password to their choice.
This user setting helps Pre-K school individual to customize their password and provides data security.

The users of our app can have comprehensive role management and user management functionalities, 
while before they may only can communicate using their internal app.