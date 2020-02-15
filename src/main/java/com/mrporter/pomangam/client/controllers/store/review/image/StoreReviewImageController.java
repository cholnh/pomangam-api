package com.mrporter.pomangam.client.controllers.store.review.image;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dsites/{didx}/stores/{sidx}/reviews/{ridx}/images")
@AllArgsConstructor
public class StoreReviewImageController {

    @GetMapping
    public ResponseEntity<?> get(
            @PathVariable(value = "didx", required = true) Long didx,
            @PathVariable(value = "sidx", required = true) Long sidx,
            @PathVariable(value = "ridx", required = true) Long ridx,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getByIdx(
            @PathVariable(value = "didx", required = true) Long didx,
            @PathVariable(value = "sidx", required = true) Long sidx,
            @PathVariable(value = "ridx", required = true) Long ridx,
            @PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> searchCount(
            @PathVariable(value = "didx", required = true) Long didx,
            @PathVariable(value = "sidx", required = true) Long sidx,
            @PathVariable(value = "ridx", required = true) Long ridx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
