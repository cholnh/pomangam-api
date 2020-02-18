package com.mrporter.pomangam._bases.files.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file);
    String storeFile(MultipartFile file, String path, String fileName);
    Resource loadFileAsResource(String fileName);
    void deleteFile(String path, boolean deepDelete);
}
