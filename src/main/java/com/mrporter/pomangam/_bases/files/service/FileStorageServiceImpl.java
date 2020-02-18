package com.mrporter.pomangam._bases.files.service;

import com.mrporter.pomangam._bases.files.service.exception.FileStorageException;
import com.mrporter.pomangam._bases.files.service.exception.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class FileStorageServiceImpl implements FileStorageService  {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public String storeFile(MultipartFile file, String path, String rename) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Make path directory
            File directoryPath = this.fileStorageLocation.resolve(path).toFile();
            if (!directoryPath.exists()) {
                try{
                    directoryPath.mkdirs();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(path+rename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    /**
     * 서버 asset 파일 삭제
     *
     * @param path 삭제할 파일 경로
     * @param deepDelete true 시, 파일 내부 내용 전부 삭제.
     */
    @Override
    public void deleteFile(String path, boolean deepDelete) {
        File directoryPath = this.fileStorageLocation.resolve(path).toFile();
        _delete(directoryPath, deepDelete);
    }

    private void _delete(File deleteFile, boolean deepDelete) {
        if(deleteFile.exists()) {
            if(deleteFile.isDirectory()) {
                for(File file : deleteFile.listFiles()) {
                    if(deepDelete && file.isDirectory()) {
                        _delete(file, true);
                    } else {
                        file.delete();
                    }
                }
                deleteFile.delete();
            } else if(deleteFile.isFile()) {
                deleteFile.delete();
            }
        }
    }
}
