package com.example.backen_kleding_bieb.service;

import com.example.backen_kleding_bieb.models.Upload;
import com.example.backen_kleding_bieb.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Service
public class UploadService {

    @Value("${my.upload_location}")
    private Path fileStoragePath;
    private final String fileStorageLocation;
    private final UploadRepository repository;

    public UploadService(@Value("${my.upload_location}") String fileStorageLocation, UploadRepository repository) {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

        this.fileStorageLocation = fileStorageLocation;
        this.repository = repository;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }

    }



    public String storeFile(MultipartFile file, String url) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path filePath = Paths.get(fileStoragePath + File.separator + fileName);


        try {
            // Controleer of de bestandsnaam veilig is
            if (fileName.contains("..")) {
                throw new IllegalArgumentException("Ongeldige bestandsnaam: " + fileName);
            }

            Path targetLocation = fileStoragePath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Kon het bestand niet opslaan: " + fileName, e);
        }
    }





    public List<byte[]> getAllFiles() {
        List<byte[]> files = new ArrayList<>();
        List<Upload> uploads = repository.findAll();

        for (Upload upload : uploads) {
            String fileName = upload.getFileName();
            try {
                Resource resource = new UrlResource(fileStoragePath.resolve(fileName).toUri());
                if (resource.exists()) {
                    files.add(resource.getInputStream().readAllBytes());
                } else {
                    throw new RuntimeException("Bestand niet gevonden: " + fileName);
                }
            } catch (IOException e) {
                throw new RuntimeException("Fout bij het lezen van bestand: " + fileName, e);
            }
        }

        return files;
    }

//
//    public Resource downLoadFile(String fileName) {
//        try {
//            Path filePath = fileStoragePath.resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//
//            if (resource.exists()) {
//                return resource;
//            } else {
//                throw new RuntimeException("Bestand niet gevonden: " + fileName);
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Bestand niet gevonden: " + fileName, e);
//        }
//    }
//
//
//    public List<byte[]> getAllFiles() {
//        List<byte[]> files = new ArrayList<>();
//        List<Upload> uploads = repository.findAll();
//
//        for (Upload upload : uploads) {
//            String fileName = upload.getFileName();
//            Resource resource = downLoadFile(fileName);
//            try {
//                files.add(resource.getContentAsByteArray());
//            } catch (IOException e) {
//                throw new RuntimeException("Issue in reading the file", e);
//            }
//        }
//
//        return files;
//    }

    /////////nuevo////
    public List<String> getFilesFromUploadDirectory() {
        List<String> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(fileStoragePath)) {
            paths.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .forEach(files::add);
        } catch (IOException e) {
            throw new RuntimeException("Issue in reading the files from upload directory", e);
        }
        return files;
    }
}


