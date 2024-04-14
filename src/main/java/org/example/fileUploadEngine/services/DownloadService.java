package org.example.fileUploadEngine.services;


import org.example.fileUploadEngine.dtos.FileForListResponseDTO;
import org.example.fileUploadEngine.entity.FileData;
import org.example.fileUploadEngine.repository.FileRepository;
import org.example.fileUploadEngine.utils.CompressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DownloadService {

    @Autowired
    private FileRepository repository;

    public byte[] downloadFile(Long fileId) throws IOException {
        Optional<FileData> dbFileData = repository.findById(fileId);
        if(dbFileData.isEmpty()) throw new FileNotFoundException();
        byte[] file= CompressUtils.decompressFile(dbFileData.get().getFileData());
        return file;
    }

    public List<FileForListResponseDTO> listFiles() {
        List<FileData> fileList = repository.findAll();
        return fileList.stream()
                .map(fileData -> new FileForListResponseDTO(fileData.getName(), fileData.getMetaData(), fileData.getCreatedAt(), fileData.getId()))
                .collect(Collectors.toList());
    }
}
