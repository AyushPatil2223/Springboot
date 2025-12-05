package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.*;


import com.example.demo.service.FileService;


@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/file")

public class FileController {
    @Autowired
    private FileService fileService;

    // UPLOAD FILE
     @PostMapping("/upload")
public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
    System.out.println("UPLOAD ENDPOINT CALLED");  // <-- debug log
    System.out.println("Received file: " + file.getOriginalFilename());
    try {
        String message = fileService.uploadFile(file);
        return ResponseEntity.ok(message);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(500).body("Error uploading file");
    }
}

    @SuppressWarnings("null")

    // DOWNLOAD FILE
    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) {

        try {
            byte[] fileData = fileService.downloadFile(filename);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + filename + "\"")
                    .body(fileData);

        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

}
