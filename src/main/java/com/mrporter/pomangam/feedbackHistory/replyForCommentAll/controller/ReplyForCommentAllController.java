package com.mrporter.pomangam.feedbackHistory.replyForCommentAll.controller;

import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.service.ReplyForCommentAllServiceImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/replyForCommentAlls")
@RestController
@AllArgsConstructor
public class ReplyForCommentAllController {

    ReplyForCommentAllServiceImpl replyForCommentAllService;

    @GetMapping
    public ResponseEntity<?> getBy(@RequestParam(value = "commentIdx", required = false) Integer commentIdx,
                                   Principal principal,
                                   PageRequest pageRequest) {
        return new ResponseEntity(replyForCommentAllService.getBy(commentIdx, principal.getName(), pageRequest), HttpStatus.OK);
    }
}
