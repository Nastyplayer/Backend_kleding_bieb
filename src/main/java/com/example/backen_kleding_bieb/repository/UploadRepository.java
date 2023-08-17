package com.example.backen_kleding_bieb.repository;

import com.example.backen_kleding_bieb.models.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadRepository extends JpaRepository<Upload, String> {
    Optional<Upload> findByFileName(String fileName);
}
