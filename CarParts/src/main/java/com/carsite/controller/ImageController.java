package com.carsite.controller;

import com.carsite.repository.AdRepository;
import com.carsite.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@RestController
public class ImageController {
    @Autowired
    Service service;
    @Autowired
    AdRepository adRepository;





    @PostMapping("upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadFile) throws Exception {
        return service.imageUploader(uploadFile);
    }
    @GetMapping("ad/{adId}/images")
    public ResponseEntity<List<String>> serveFiles(@PathVariable Long adId) {
        return service.getAdImages(adId);
    }
    @GetMapping("images")
    public List<String> getAllImages() {
        return service.getAllImageFiles();
    }
    @GetMapping("images/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws IOException {
        return service.imageByFileName(filename);
    }


}
