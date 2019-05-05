package com.mrporter.pomangam.feedbackHistory.commentStore.controller;

import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreInputDto;
import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreViewDto;
import com.mrporter.pomangam.feedbackHistory.commentStore.service.CommentStoreServiceImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/commentStores")
@RestController
@AllArgsConstructor
public class CommentStoreController {

    CommentStoreServiceImpl commentStoreService;

    @GetMapping("/search/findByStoreIdx")
    public ResponseEntity findByStoreIdx(@RequestParam("storeIdx") Integer storeIdx,
                                         @RequestParam(value = "orderBy", required = false) String orderBy,
                                         Principal principal,
                                         PageRequest pageRequest) {
        List<CommentStoreViewDto> comments = commentStoreService.findByStoreIdx(storeIdx, orderBy, principal.getName(), pageRequest);
        return new ResponseEntity(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody CommentStoreInputDto dto) {
        return new ResponseEntity(commentStoreService.saveCommentStoreInput(dto), HttpStatus.OK);
    }

    @DeleteMapping("/{commentStoreIdx}")
    public ResponseEntity delete(@PathVariable(name = "commentStoreIdx") Integer commentStoreIdx) {
        commentStoreService.delete(commentStoreIdx);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{commentStoreIdx}/like")
    public ResponseEntity like(@PathVariable(name = "commentStoreIdx") Integer commentStoreIdx,
                               Principal principal) {
        commentStoreService.like(commentStoreIdx, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{commentStoreIdx}/unlike")
    public ResponseEntity unlike(@PathVariable(name = "commentStoreIdx") Integer commentStoreIdx,
                                 Principal principal) {
        commentStoreService.unlike(commentStoreIdx, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
    }
}
