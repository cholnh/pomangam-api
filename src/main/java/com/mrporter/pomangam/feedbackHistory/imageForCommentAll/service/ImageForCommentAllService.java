package com.mrporter.pomangam.feedbackHistory.imageForCommentAll.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageForCommentAllService {
    void saveImages(Integer commentAllIdx, MultipartFile[] files);
    void deleteImage(String fileName);
}
