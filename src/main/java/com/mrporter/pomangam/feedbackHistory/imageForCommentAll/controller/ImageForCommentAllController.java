package com.mrporter.pomangam.feedbackHistory.imageForCommentAll.controller;

import com.mrporter.pomangam.feedbackHistory.imageForCommentAll.service.ImageForCommentAllServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/imageForCommentAlls")
@RestController
@AllArgsConstructor
public class ImageForCommentAllController {

    ImageForCommentAllServiceImpl imageForCommentAllService;

    @PostMapping
    public ResponseEntity post(@RequestParam("files") MultipartFile[] files,
                               @RequestParam("commentAllIdx") Integer commentAllIdx) {
        imageForCommentAllService.saveImages(commentAllIdx, files);
        return new ResponseEntity(HttpStatus.OK);
    }
}
