package com.mrporter.pomangam.feedbackHistory.commentAll.controller;

import com.mrporter.pomangam.feedbackHistory.commentAll.service.CommentAllServiceImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/commentAlls")
@RestController
@AllArgsConstructor
public class CommentAllController {

    CommentAllServiceImpl commentAllService;

    @GetMapping
    public ResponseEntity<?> getBy(@RequestParam(value = "deliverySiteIdx", required = false) Integer deliverySiteIdx,
                                   @RequestParam(value = "storeIdx", required = false) Integer storeIdx,
                                   @RequestParam(value = "orderBy", required = false) String orderBy,
                                   PageRequest pageRequest) {
        return new ResponseEntity(commentAllService.getBy(deliverySiteIdx, storeIdx, orderBy, pageRequest), HttpStatus.OK);
    }
}
