package com.mrporter.pomangam.feedbackHistory.commentStore.controller;

import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreViewDto;
import com.mrporter.pomangam.feedbackHistory.commentStore.service.CommentStoreServiceImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/commentStores")
@RestController
@AllArgsConstructor
public class CommentStoreController {

    CommentStoreServiceImpl commentStoreService;

    @GetMapping("/search/findByStoreIdx")
    public ResponseEntity<?> findByStoreIdx(@RequestParam("storeIdx") Integer storeIdx,
                                            @RequestParam(value = "orderBy", required = false) String orderBy,
                                            PageRequest pageRequest) {
        List<CommentStoreViewDto> comments = commentStoreService.findByStoreIdx(storeIdx, orderBy, pageRequest);
        return new ResponseEntity(comments, HttpStatus.OK);

    }
}
