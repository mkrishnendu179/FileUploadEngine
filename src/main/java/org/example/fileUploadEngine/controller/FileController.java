package org.example.fileUploadEngine.controller;

import org.example.fileUploadEngine.dtos.FileForListResponseDTO;
import org.example.fileUploadEngine.dtos.UpdateFileRequestDto;
import org.example.fileUploadEngine.dtos.UploadFileDTO;
import org.example.fileUploadEngine.services.DownloadService;
import org.example.fileUploadEngine.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    UploadService uploadService;

    @Autowired
    DownloadService downloadService;
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("fileName") String fileName,
                                             @RequestParam(value = "metadata", required = false) String metadata) throws IOException {
        try{
        UploadFileDTO uploadFileDTO = new UploadFileDTO();
        uploadFileDTO.setFileData(file);
        uploadFileDTO.setName(fileName);
        uploadFileDTO.setMetaData(metadata);
        Long uploadFileId = uploadService.uploadFile(uploadFileDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadFileId);
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
    }

    }


    @GetMapping("/{fileId}")
    public ResponseEntity<?> readFile(@PathVariable Long fileId) throws IOException {
        try {
            byte[] fileData = downloadService.downloadFile(fileId);
            return ResponseEntity.ok().body(fileData);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found.");
        }
    }

    @PutMapping("/{fileId}")
    public ResponseEntity<String> updateFile(@PathVariable Long fileId,
                                             @RequestParam(value = "file", required = false) MultipartFile file,
                                             @RequestParam(value = "metadata", required = false) String metadata) {
        try {
            UpdateFileRequestDto updateFileRequestDto = new UpdateFileRequestDto();
            updateFileRequestDto.setFileData(file);
            updateFileRequestDto.setMetaData(metadata);
            updateFileRequestDto.setId(fileId);
            uploadService.updateFile(updateFileRequestDto);
            return ResponseEntity.ok().body("File updated successfully.");
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update file.");
        }
    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<String> deleteFile(@PathVariable Long fileId) {
        try {
            uploadService.deleteFile(fileId);
            return ResponseEntity.ok().body("File deleted successfully. FileID - " + fileId);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found.FileID - " + fileId);
        }
    }

    @GetMapping
    public ResponseEntity<List<FileForListResponseDTO>> listFiles() {
        List<FileForListResponseDTO> files = downloadService.listFiles();
        return ResponseEntity.ok().body(files);
    }




}
