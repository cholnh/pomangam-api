package com.mrporter.pomangam.client.controllers.store.review.reply;

import com.fasterxml.jackson.annotation.JsonView;
import com.mrporter.pomangam.client.domains.store.review.reply.StoreReviewReplyDto;
import com.mrporter.pomangam.client.domains.store.review.reply.StoreReviewReplyDtoView;
import com.mrporter.pomangam.client.services.store.review.reply.StoreReviewReplyServiceImpl;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/dsites/{dIdx}/stores/{sIdx}/reviews/{rIdx}/replies")
@AllArgsConstructor
public class StoreReviewReplyController {

    StoreReviewReplyServiceImpl storeReviewReplyService;
    UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<?> findByIdxStoreReview(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "rIdx", required = true) Long rIdx,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable,
            Principal principal
    ) {
        Long uIdx = userService.findIdxByPhoneNumber(principal.getName());
        return new ResponseEntity(storeReviewReplyService.findByIdxStoreReview(rIdx, uIdx, pageable), HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> findByIdx(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "rIdx", required = true) Long rIdx,
            @PathVariable(value = "idx", required = true) Long idx,
            Principal principal
    ) {
        Long uIdx = userService.findIdxByPhoneNumber(principal.getName());
        return new ResponseEntity(storeReviewReplyService.findByIdx(idx, uIdx), HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> count(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "rIdx", required = true) Long rIdx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PostMapping
    @JsonView(StoreReviewReplyDtoView.CustomView.class)
    public ResponseEntity<?> post(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "rIdx", required = true) Long rIdx,
            StoreReviewReplyDto dto,
            Principal principal
    ) {
        Long uIdx = userService.findIdxByPhoneNumber(principal.getName());
        dto.setIdxUser(uIdx);   // 댓글작성자 등록 (who)
        dto.setIdxStoreReview(rIdx);    // 리뷰 인덱스 등록 (where)
        return new ResponseEntity(storeReviewReplyService.save(dto), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PatchMapping("/{rpIdx}")
    @JsonView(StoreReviewReplyDtoView.CustomView.class)
    public ResponseEntity<?> patch(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "rIdx", required = true) Long rIdx,
            @PathVariable(value = "rpIdx", required = true) Long rpIdx,
            StoreReviewReplyDto dto,
            Principal principal
    ) {
        Long uIdx = userService.findIdxByPhoneNumber(principal.getName());
        if(storeReviewReplyService.findByIdx(rpIdx, uIdx).getIsOwn()) {
            dto.setIdx(rpIdx);
            dto.setIdxUser(uIdx);
            dto.setIdxStoreReview(rIdx);
            return new ResponseEntity(storeReviewReplyService.update(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @DeleteMapping("/{rpIdx}")
    public ResponseEntity<?> delete(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "rIdx", required = true) Long rIdx,
            @PathVariable(value = "rpIdx", required = true) Long rpIdx,
            Principal principal
    ) {
        Long uIdx = userService.findIdxByPhoneNumber(principal.getName());
        if(storeReviewReplyService.findByIdx(rpIdx, uIdx).getIsOwn()) {
            storeReviewReplyService.delete(rIdx, rpIdx);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}
