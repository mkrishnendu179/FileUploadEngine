package org.example.fileUploadEngine.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateFileRequestDto {
    private String name;
    private String metaData;
    private MultipartFile fileData;
    private Long id;
}
