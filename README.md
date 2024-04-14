# File Upload Service

This project is a Spring Boot application for managing file uploads.

## Setup Database Connection

To connect the application to your own MySQL database, follow these steps:

1. **Open `application.properties`:** Navigate to `src/main/resources/application.properties` in the project directory.

2. **Update Database Configuration:**
   - `server.port`: Set the desired port number for the application to run on (e.g., `9191`).
   - `spring.datasource.url`: Replace `${MYSQL_HOST:localhost}` with your MySQL host address and update the port (`:3306`) if necessary. Also, update `fileuploadservice` to your desired database name.
   - `spring.datasource.username` and `spring.datasource.password`: Update these fields with your MySQL username and password.
   - `spring.datasource.driver-class-name`: Ensure that the driver class name is correct for MySQL (`com.mysql.cj.jdbc.Driver`).

3. **Save Changes:** Save the `application.properties` file after making the necessary modifications.

## Running the Application

To run the Spring Boot project, follow these steps:

1. **Clone the Repository:** Clone this repository to your local machine using Git:

   
   git clone repository-url
    
2.  **Build the Project:** Run the following Maven command to build the project:

  
  mvn clean package

3.  **Run the Application:** Execute the generated JAR file to run the Spring Boot application:
   
  java -jar target/file-upload-service.jar
  
4.  **Access the Application:** Once the application is running, you can access it at http://localhost:9191 (you can change it from application.properties)


# File Management APIs

This document provides details about the RESTful APIs for file management provided by the FileController.

## Base URL

All endpoints are relative to the base URL: `/files`

## API Endpoints

### Upload File

- **Endpoint:** `POST /upload`
- **Description:** Allows users to upload files onto the platform.
- **Input:** 
  - `file`: The binary data of the file to be uploaded.
  - `fileName`: The name of the file.
  - `metadata` (optional): Additional metadata associated with the file.
- **Output:** A unique file identifier.
- **Response:** 
  - `200 OK` on successful upload with the unique file identifier.
  - `500 Internal Server Error` if upload fails.

### Read File

- **Endpoint:** `GET /{fileId}`
- **Description:** Retrieve a specific file based on a unique identifier.
- **Input:** Unique file identifier (`fileId`).
- **Output:** File binary data.
- **Response:** 
  - `200 OK` with the file binary data.
  - `404 Not Found` if the file with the specified `fileId` is not found.

### Update File

- **Endpoint:** `PUT /{fileId}`
- **Description:** Update an existing file or its metadata.
- **Input:** 
  - `file` (optional): New file binary data.
  - `metadata` (optional): New metadata.
- **Output:** A success message.
- **Response:** 
  - `200 OK` on successful update.
  - `404 Not Found` if the file with the specified `fileId` is not found.
  - `500 Internal Server Error` if update fails.

### Delete File

- **Endpoint:** `DELETE /{fileId}`
- **Description:** Delete a specific file based on a unique identifier.
- **Input:** Unique file identifier (`fileId`).
- **Output:** A success or failure message.
- **Response:** 
  - `200 OK` on successful deletion.
  - `404 Not Found` if the file with the specified `fileId` is not found.

### List Files

- **Endpoint:** `GET /`
- **Description:** List all available files and their metadata.
- **Input:** None.
- **Output:** A list of file metadata objects.
- **Response:** 
  - `200 OK` with a list of file metadata objects.
