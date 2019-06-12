package com.mrporter.pomangam.feedbackHistory.replyForCommentAll.controller;

import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain.ReplyForCommentAllInputDto;
import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.service.ReplyForCommentAllServiceImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity post(@RequestBody ReplyForCommentAllInputDto dto) {
        return new ResponseEntity(replyForCommentAllService.saveReplyForCommentAllInput(dto), HttpStatus.OK);
    }

    @PatchMapping("/{replyIdx}")
    public ResponseEntity<?> patch(@PathVariable(name = "replyIdx") Integer replyIdx,
                                   @RequestBody ReplyForCommentAllInputDto dto) {
        return new ResponseEntity(replyForCommentAllService.patch(replyIdx, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{replyIdx}")
    public ResponseEntity delete(@PathVariable(name = "replyIdx") Integer replyIdx) {
        return new ResponseEntity(replyForCommentAllService.delete(replyIdx), HttpStatus.OK);
    }

    @GetMapping("/{replyIdx}/like")
    public ResponseEntity like(@PathVariable(name = "replyIdx") Integer replyIdx,
                               Principal principal) {
        replyForCommentAllService.like(replyIdx, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{replyIdx}/unlike")
    public ResponseEntity unlike(@PathVariable(name = "replyIdx") Integer replyIdx,
                                 Principal principal) {
        replyForCommentAllService.unlike(replyIdx, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
    }
}
