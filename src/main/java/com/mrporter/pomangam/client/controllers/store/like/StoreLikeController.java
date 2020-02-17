package com.mrporter.pomangam.client.controllers.store.like;

import com.mrporter.pomangam.client.services.store.like.StoreLikeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/dsites/{dIdx}/stores/{sIdx}/likes")
@AllArgsConstructor
public class StoreLikeController {

    StoreLikeServiceImpl storeLikeService;

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PatchMapping("/like")
    public ResponseEntity<?> like(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            Principal principal
    ) {
        storeLikeService.like(principal.getName(), sIdx);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PatchMapping("/unlike")
    public ResponseEntity<?> unlike(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            Principal principal
    ) {
        storeLikeService.cancelLike(principal.getName(), sIdx);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PatchMapping("/toggle")
    public ResponseEntity<Boolean> toggle(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            Principal principal
    ) {
        return new ResponseEntity(storeLikeService.toggle(principal.getName(), sIdx), HttpStatus.OK);
    }
}
