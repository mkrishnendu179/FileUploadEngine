package org.example.fileUploadEngine.services;

import org.example.fileUploadEngine.dtos.UpdateFileRequestDto;
import org.example.fileUploadEngine.dtos.UploadFileDTO;
import org.example.fileUploadEngine.entity.FileData;
import org.example.fileUploadEngine.repository.FileRepository;
import org.example.fileUploadEngine.utils.CompressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UploadService {
    @Autowired
    private FileRepository repository;

    public static String getCurrentDateTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        return currentTime.format(formatter);
    }
    public Long uploadFile(UploadFileDTO uploadFileDTO) throws IOException {
        MultipartFile file = uploadFileDTO.getFileData();
        String createdAt = getCurrentDateTime();
        FileData fileData = repository.save(FileData.builder()
                .name(uploadFileDTO.getName())
                .metaData(uploadFileDTO.getMetaData())
                .createdAt(createdAt)
                .fileData(CompressUtils.compressFile(file.getBytes())).build());
        if (fileData == null) {
            throw new IOException();
        }
        return fileData.getId();
    }

    public void updateFile(UpdateFileRequestDto updateFileRequestDto) throws IOException {
        MultipartFile file = updateFileRequestDto.getFileData();
        String metaData = updateFileRequestDto.getMetaData();
        Optional<FileData> dbFileData = repository.findById(updateFileRequestDto.getId());
        if (dbFileData.isPresent()){
            FileData existingFileData = dbFileData.get();
            if (file != null) {
                existingFileData.setFileData(CompressUtils.compressFile(file.getBytes()));
            }
            if (metaData != null) {
                existingFileData.setMetaData(metaData);
            }
            repository.save(existingFileData);
        } else{
            throw new FileNotFoundException("File not found with id: " + updateFileRequestDto.getId());
        }
    }

    public void deleteFile(Long fileId) throws FileNotFoundException {
        Optional<FileData> optionalFileData = repository.findById(fileId);
        if (optionalFileData.isPresent()) {
            FileData fileData = optionalFileData.get();
            repository.delete(fileData);
        } else {
            throw new FileNotFoundException("File not found with id: " + fileId);
        }
    }



}
