package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    // Upload folder inside project directory
    private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    // -----------------------------
    // 1️⃣ UPLOAD FILE
    // -----------------------------
    public String uploadFile(MultipartFile file) throws IOException {

        File folder = new File(UPLOAD_DIR);
        if (!folder.exists()) {
            folder.mkdirs();   // Create folder if not exist
        }

        String filePath = UPLOAD_DIR + file.getOriginalFilename();

        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        System.out.println("UPLOAD FOLDER: " + UPLOAD_DIR);
        System.out.println("FILE SAVED AT: " + filePath);

        return "File uploaded: " + file.getOriginalFilename();
    }


    // -----------------------------
    // 2️⃣ DOWNLOAD FILE
    // -----------------------------
    public byte[] downloadFile(String fileName) throws IOException {
        String filePath = UPLOAD_DIR + fileName;
        return Files.readAllBytes(Paths.get(filePath));
    }


    // -----------------------------
    // 3️⃣ GET ALL FILES
    // -----------------------------
    public List<String> getAllFiles() {

        File folder = new File(UPLOAD_DIR); // MUST use same upload folder
        File[] files = folder.listFiles();

        List<String> fileNames = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }

        return fileNames;
    }
}
