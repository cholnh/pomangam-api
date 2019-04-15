package com.mrporter.pomangam.feedbackHistory.commentStore.controller;

import com.mrporter.pomangam.feedbackHistory.commentStore.service.CommentStoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/commentStores")
@RestController
@AllArgsConstructor
public class CommentStoreController {

    CommentStoreServiceImpl commentStoreService;

    @GetMapping("/search/getBlahBlah")
    public ResponseEntity<?> getBlahBlah(@RequestParam("blah") String blah) {
        return new ResponseEntity(blah, HttpStatus.OK);
    }
}
