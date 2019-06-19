package com.mrporter.pomangam.feedbackHistory.imageForCommentAll.service;

import com.mrporter.pomangam.common.file.service.FileStorageServiceImpl;
import com.mrporter.pomangam.feedbackHistory.imageForCommentAll.domain.ImageForCommentAll;
import com.mrporter.pomangam.feedbackHistory.imageForCommentAll.repository.ImageForCommentAllJpaRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@AllArgsConstructor
public class ImageForCommentAllServiceImpl implements ImageForCommentAllService {

    FileStorageServiceImpl fileStorageService;
    ImageForCommentAllJpaRepository imageForCommentAllJpaRepository;

    @Override
    public void saveImages(Integer commentAllIdx, MultipartFile[] files) {
        for(int i=0; i<files.length; i++) {
            MultipartFile file = files[i];
            ImageForCommentAll imageForCommentAll = ImageForCommentAll.builder()
                    .commentAllIdx(commentAllIdx)
                    .type(i==0?Byte.valueOf("0"):Byte.valueOf("1"))
                    .build();
            ImageForCommentAll saved = imageForCommentAllJpaRepository.save(imageForCommentAll);

            Integer idx = saved.getIdx();
            String disk = "C:";
            String path = "/assets/image/comment/post/";
            String name = idx+"."+FilenameUtils.getExtension(file.getOriginalFilename());
            saved.setImgpath(path+name);
            fileStorageService.storeFile(file, disk+path, name);
            imageForCommentAllJpaRepository.save(saved);
        }
    }

    @Override
    public void deleteImage(String fileName) {
        String disk = "C:";
        String path = disk+"/assets/image/comment/post/"+fileName;
        File file = new File(path);
        if(file.exists()) {
            file.delete();
        }
    }
}