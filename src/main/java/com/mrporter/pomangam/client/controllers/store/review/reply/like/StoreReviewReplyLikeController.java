package com.mrporter.pomangam.client.controllers.store.review.reply.like;

import com.mrporter.pomangam.client.services.store.review.reply.like.StoreReviewReplyLikeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/dsites/{dIdx}/stores/{sIdx}/reviews/{rIdx}/replies/{rpIdx}/likes")
@AllArgsConstructor
public class StoreReviewReplyLikeController {

    StoreReviewReplyLikeServiceImpl storeReviewReplyLikeService;

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PatchMapping("/like")
    public ResponseEntity<?> like(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "rIdx", required = true) Long rIdx,
            @PathVariable(value = "rpIdx", required = true) Long rpIdx,
            Principal principal
    ) {
        storeReviewReplyLikeService.like(principal.getName(), rpIdx);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PatchMapping("/unlike")
    public ResponseEntity<?> unlike(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "rIdx", required = true) Long rIdx,
            @PathVariable(value = "rpIdx", required = true) Long rpIdx,
            Principal principal
    ) {
        storeReviewReplyLikeService.cancelLike(principal.getName(), rpIdx);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PatchMapping("/toggle")
    public ResponseEntity<Boolean> toggle(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "rIdx", required = true) Long rIdx,
            @PathVariable(value = "rpIdx", required = true) Long rpIdx,
            Principal principal
    ) {
        return new ResponseEntity(storeReviewReplyLikeService.toggle(principal.getName(), rpIdx), HttpStatus.OK);
    }
}
