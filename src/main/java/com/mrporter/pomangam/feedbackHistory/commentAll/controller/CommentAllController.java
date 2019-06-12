package com.mrporter.pomangam.feedbackHistory.commentAll.controller;

import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllInputDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.service.CommentAllServiceImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @GetMapping("/{commentIdx}")
    public ResponseEntity<?> getDetail(@PathVariable(value = "commentIdx") Integer commentIdx,
                                       Principal principal) {
        return new ResponseEntity(commentAllService.getDetail(commentIdx, principal.getName()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody CommentAllInputDto dto) {
        return new ResponseEntity(commentAllService.saveCommentAllInput(dto), HttpStatus.OK);
    }

    @PatchMapping("/{commentIdx}")
    public ResponseEntity<?> patch(@PathVariable(name = "commentIdx") Integer commentIdx,
                                   @RequestBody CommentAllInputDto dto) {
        return new ResponseEntity(commentAllService.patch(commentIdx, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{commentIdx}")
    public ResponseEntity delete(@PathVariable(name = "commentIdx") Integer commentIdx) {
        return new ResponseEntity(commentAllService.delete(commentIdx), HttpStatus.OK);
    }

    @GetMapping("/{commentAllIdx}/like")
    public ResponseEntity like(@PathVariable(name = "commentAllIdx") Integer commentAllIdx,
                               Principal principal) {
        commentAllService.like(commentAllIdx, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{commentAllIdx}/unlike")
    public ResponseEntity unlike(@PathVariable(name = "commentAllIdx") Integer commentAllIdx,
                                 Principal principal) {
        commentAllService.unlike(commentAllIdx, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
    }
}
