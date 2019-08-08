package com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.controller;

import com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.service.ImageForCommentAllMainServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images/cmtAdvertiseForMain")
@AllArgsConstructor
public class ImageForCommentAllMainController {

    ImageForCommentAllMainServiceImpl cmtAdvertiseForMainService;

    @GetMapping
    public ResponseEntity<?> getImages(@RequestParam(value = "deliverySiteIdx", required = true) Integer deliverySiteIdx) {
        return new ResponseEntity<>(cmtAdvertiseForMainService.getImageForCommentAllMainByDeliverySiteIdx(deliverySiteIdx), HttpStatus.OK);
    }

}
