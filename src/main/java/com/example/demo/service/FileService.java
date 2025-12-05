package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    // Save uploads inside your project folder always
    private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    public String uploadFile(MultipartFile file) throws IOException {

    File folder = new File(UPLOAD_DIR);
    if (!folder.exists()) {
        folder.mkdirs();
    }

    String filePath = UPLOAD_DIR + file.getOriginalFilename();

    Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

    System.out.println("UPLOAD FOLDER: " + UPLOAD_DIR);
    System.out.println("FILE SAVED AT: " + filePath);

    return "File uploaded: " + file.getOriginalFilename();
}


    public byte[] downloadFile(String fileName) throws IOException {
        String filePath = UPLOAD_DIR + fileName;
        return Files.readAllBytes(Paths.get(filePath));
    }
}
