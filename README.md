# project10
## Overview
This project is a simplified backend system for managing users, patients, and heart rate data. It is built using Spring Boot and provides RESTful APIs to handle various functionalities. The system allows for user registration and login, patient management, and recording/retrieving heart rate data for patients.

---

## Modules

### 1. *User Module*
Manages user registration, login, and retrieval of user information.

#### Data Model: User
- *Fields*:
  - id (Long): Unique identifier for the user (auto-generated).
  - email (String): User's email address (unique and required).
  - password (String): User's password (required, hashed before storage).
  - role (Role): User's role (ADMIN, USER, or DOCTOR).
  - username (String): Derived from the email (e.g., user@example.com → user).

#### Role Enum
java
public enum Role {
    USER,
    ADMIN,
    DOCTOR
}


#### Service: UserService
- *Methods*:
  - registerUser: Registers a new user with email, password, and role.
  - loginUser: Authenticates a user by matching email and password.
  - getAllUsers: Retrieves a list of all users.

#### Controller: UserController
- *Endpoints*:
  - POST /users/register: Register a new user.
  - POST /users/login: Login an existing user.
  - GET /users/: Retrieve all users.

---

### 2. *Patient Module*
Manages patient information and associates patients with doctors.

#### Data Model: Patient
- *Fields*:
  - id (Long): Unique identifier for the patient (auto-generated).
  - name (String): Patient's name (required).
  - age (int): Patient's age (must be a positive number).
  - disease (String): Description of the patient's disease (required).
  - doctor (User): The doctor associated with the patient (required).

#### Service: PatientService
- *Methods*:
  - addPatient: Adds a new patient with the provided details and associates them with a doctor.
  - getAllPatients: Retrieves a list of all patients.

#### Controller: PatientController
- *Endpoints*:
  - POST /patients/register: Add a new patient.
  - GET /patients/: Retrieve all patients.

---

### 3. *Heart Rate Module*
Manages heart rate data for patients.

#### Data Model: HeartRate
- *Fields*:
  - id (Long): Unique identifier for the heart rate record (auto-generated).
  - bpm (int): Heart rate in beats per minute.
  - recordedAt (LocalDateTime): Timestamp of the recording.
  - patient (Patient): The patient associated with the heart rate record.

#### Service: HeartRateService
- *Methods*:
  - recordHeartRate: Records heart rate data for a patient.
  - getHeartRateHistory: Retrieves heart rate history for a patient.

#### Controller: HeartRateController
- *Endpoints*:
  - POST /patients/{patientId}/heart-rate: Record heart rate data for a patient.
  - GET /patients/{patientId}/heart-rate: Retrieve heart rate history for a patient.

---

## API Documentation

### User Endpoints

1. *Register a User*
   - *URL*: POST /users/register
   - *Request Body*:
     json
     {
       "email": "user@example.com",
       "password": "password123",
       "role": "USER"
     }
     
   - *Response*:
     json
     "User registered successfully!"
     

2. *Login a User*
   - *URL*: POST /users/login
   - *Request Params*:
     - email: User's email.
     - password: User's password.
   - *Response*:
     json
     "Login successful!"
     

3. *Get All Users*
   - *URL*: GET /users/
   - *Response*:
     json
     [
       {
         "id": 1,
         "email": "user@example.com",
         "role": "USER",
         "username": "user"
       }
     ]
     

---

### Patient Endpoints

1. *Add a Patient*
   - *URL*: POST /patients/register
   - *Request Body*:
     json
     {
       "name": "John Doe",
       "age": 30,
       "disease": "Hypertension",
       "doctorId": 1
     }
     
   - *Response*:
     json
     {
       "id": 1,
       "name": "John Doe",
       "age": 30,
       "disease": "Hypertension",
       "doctor": {
         "id": 1,
         "email": "doctor@example.com",
         "role": "DOCTOR",
         "username": "doctor"
       }
     }
     

2. *Get All Patients*
   - *URL*: GET /patients/
   - *Response*:
     json
     [
       {
         "id": 1,
         "name": "John Doe",
         "age": 30,
         "disease": "Hypertension",
         "doctor": {
           "id": 1,
           "email": "doctor@example.com",
           "role": "DOCTOR",
           "username": "doctor"
         }
       }
     ]
     

---

### Heart Rate Endpoints

1. *Record Heart Rate*
   - *URL*: POST /patients/{patientId}/heart-rate
   - *Request Params*:
     - bpm: Heart rate in beats per minute.
   - *Response*:
     json
     {
       "id": 1,
       "bpm": 72,
       "recordedAt": "2023-10-01T12:34:56Z",
       "patient": {
         "id": 1,
         "name": "John Doe",
         "age": 30,
         "disease": "Hypertension"
       }
     }
     

2. *Get Heart Rate History*
   - *URL*: GET /patients/{patientId}/heart-rate
   - *Response*:
     json
     [
       {
         "id": 1,
         "bpm": 72,
         "recordedAt": "2023-10-01T12:34:56Z",
         "patient": {
           "id": 1,
           "name": "John Doe",
           "age": 30,
           "disease": "Hypertension"
         }
       }
     ]
     

---

## Assumptions and Decisions
1. *Authentication*: Simplified email and password matching is used for login.
2. *Role Handling*: Roles are validated and converted to uppercase (ADMIN, USER, or DOCTOR).
3. *Password Security*: Passwords are hashed using BCryptPasswordEncoder before storage.
4. *Data Validation*: Basic validation is applied to fields like name, age, and disease.
5. *JSON Serialization*: The @JsonIgnore annotation is used to prevent infinite recursion when serializing relationships.

---

## Setup and Running the Project

### Prerequisites
- Java Development Kit (JDK) 11 or higher.
- Maven for dependency management.
- MySQL or any other relational database.

### Steps to Run the Project
1. *Clone the Repository*:
   bash
   git clone <repository-url>
   cd <project-directory>
   

2. *Configure Database*:
   - Update application.properties with your database credentials:
     properties
     spring.datasource.url=jdbc:mysql://localhost:3306/janitri
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     

3. *Build the Project*:
   bash
   mvn clean install
   

4. *Run the Application*:
   bash
   mvn spring-boot:run
   

5. *Access the APIs*:
   - Use tools like Postman or curl to interact with the APIs.

---

## Conclusion
This documentation provides a comprehensive guide to setting up, running, and interacting with the Janitri Backend Assignment project. The APIs are designed to be intuitive and easy to use, with a focus on clean and maintainable code.
