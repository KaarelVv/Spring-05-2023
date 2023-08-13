package com.carsite.service;

import com.carsite.entity.Ad;
import com.carsite.entity.Images;
import com.carsite.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
public class Service {
    @Autowired
    AdRepository adRepository;
    private final ResourceLoader resourceLoader;
    public Service (ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    private static final String AD_IMAGE_FOLDER = "F:\\DbImages\\AdImages";
    public ResponseEntity<String> imageUploader(MultipartFile uploadFile) throws Exception {
        if (uploadFile.isEmpty()) {
            return new ResponseEntity<>("please select a file", HttpStatus.OK);
        } else {
            byte[] bytes = uploadFile.getBytes();
            Path path = Paths.get(AD_IMAGE_FOLDER).resolve(uploadFile.getOriginalFilename());
            Files.write(path, bytes);

            return new ResponseEntity<>(
                    uploadFile.getOriginalFilename(),
                    new HttpHeaders(),
                    HttpStatus.OK);
        }
    }

    public static List<String> getAllImageFiles() {
        File folder = new File(AD_IMAGE_FOLDER);
        File[] listOfFiles = folder.listFiles();
        return Arrays.stream(listOfFiles)
                .map(File::getName)
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<String>> getAdImages(Long adId) {
        Optional<Ad> optionalAd = adRepository.findById(adId);
        if (optionalAd.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        List<Images> adImagesList = optionalAd.get().getAdImages();
        List<String> imagePaths = new ArrayList<>();
        for (Images adImages : adImagesList) {
            String filename = adImages.getImageName();
            String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/images/")
                    .path(filename)
                    .toUriString();
            imagePaths.add(imageUrl);
        }
        return ResponseEntity.ok(imagePaths);
    }
    public ResponseEntity<Resource> imageByFileName(String filename) throws IOException {
        Resource file = resourceLoader.getResource("file:" + Paths.get(AD_IMAGE_FOLDER, filename));
        String fileType = Files.probeContentType(Paths.get(AD_IMAGE_FOLDER, filename));
        fileType = fileType != null ? fileType : MediaType.APPLICATION_OCTET_STREAM_VALUE;
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
    public void imageAttach(MultipartFile[] multipartFile, Ad ad) throws Exception {
        if (multipartFile == null || multipartFile.length == 0) {
            Images adImages = new Images();
            adImages.setImageName("");
            adImages.setAd(ad);
            ad.getAdImages().add(adImages);
        } else {
            for (MultipartFile file : multipartFile) {
                ResponseEntity<?> uploadResponse = imageUploader(file);
                if (uploadResponse.getStatusCode() == HttpStatus.OK) {
                    Images adImages = new Images();
                    adImages.setImageName((String) uploadResponse.getBody());
                    adImages.setAd(ad);
                    ad.getAdImages().add(adImages);
                }
            }
        }
    }
}
