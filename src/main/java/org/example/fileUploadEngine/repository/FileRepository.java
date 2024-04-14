package org.example.fileUploadEngine.repository;

import org.example.fileUploadEngine.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileData,Long> {
    Optional<FileData> findById(Long id);

    @Override
    FileData save( FileData fileData);


}
