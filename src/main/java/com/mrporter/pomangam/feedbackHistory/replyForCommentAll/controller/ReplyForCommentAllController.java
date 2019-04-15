package com.mrporter.pomangam.feedbackHistory.replyForCommentAll.controller;

import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.service.ReplyForCommentAllServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/replyForCommentAlls")
@RestController
@AllArgsConstructor
public class ReplyForCommentAllController {

    ReplyForCommentAllServiceImpl replyForCommentAllService;

    @GetMapping("/search/getBlahBlah")
    public ResponseEntity<?> getBlahBlah(@RequestParam("blah") String blah) {
        return new ResponseEntity(blah, HttpStatus.OK);
    }
}
