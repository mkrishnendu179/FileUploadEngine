package org.example.fileUploadEngine.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileForListResponseDTO {
    String fileName;
    String metaData;

    String createdAt;

    Long id;

    public FileForListResponseDTO(String fileName, String metaData, String createdAt, Long id) {
        this.fileName = fileName;
        this.metaData = metaData;
        this.createdAt = createdAt;
        this.id = id;
    }
}
