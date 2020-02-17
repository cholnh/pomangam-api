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
@RequestMapping("/dsites/{dIdx}/stores")
@AllArgsConstructor
public class StoreController {

    StoreServiceImpl storeService;

    @GetMapping
    public ResponseEntity<?> findByIdxDeliverySite(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(storeService.findByIdxDeliverySite(dIdx, pageable), HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> findByIdx(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(storeService.findByIdx(idx), HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> count(
            @PathVariable(value = "dIdx", required = true) Long dIdx
    ) {
        return new ResponseEntity(storeService.count(), HttpStatus.OK);
    }

    @GetMapping("/search/summaries")
    public ResponseEntity<?> searchSummaries(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(storeService.findSummaries(dIdx, pageable), HttpStatus.OK);
    }
}
