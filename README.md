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
