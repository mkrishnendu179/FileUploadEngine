package org.example.fileUploadEngine.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadFileDTO {
    private String name;
    private String metaData;
    private MultipartFile fileData;
}
