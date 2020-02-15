package com.mrporter.pomangam.client.controllers.store;

import com.mrporter.pomangam.client.services.store.StoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dsites/{didx}/stores")
@AllArgsConstructor
public class StoreController {

    StoreServiceImpl storeService;

    @GetMapping
    public ResponseEntity<?> get(
            @PathVariable(value = "didx", required = true) Integer didx,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(storeService.get(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getByIdx(
            @PathVariable(value = "didx", required = true) Integer didx,
            @PathVariable(value = "idx", required = true) Integer idx
    ) {
        return new ResponseEntity(storeService.getByIdx(idx), HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> searchCount(
            @PathVariable(value = "didx", required = true) Integer didx
    ) {
        return new ResponseEntity(storeService.count(), HttpStatus.OK);
    }

    @GetMapping("/search/summaries")
    public ResponseEntity<?> searchSummary(
            @PathVariable(value = "didx", required = true) Integer didx
    ) {
        return new ResponseEntity(storeService.getSummaries(didx), HttpStatus.OK);
    }
}
