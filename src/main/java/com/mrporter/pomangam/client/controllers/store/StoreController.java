package com.mrporter.pomangam.client.controllers.store;

import com.mrporter.pomangam.client.services.store.StoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/dsites/{dIdx}/stores")
@AllArgsConstructor
public class StoreController {

    StoreServiceImpl storeService;

    @GetMapping
    public ResponseEntity<?> findByIdxDeliverySite(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @RequestParam(value = "oIdx", required = false) Long oIdx,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(value = "oDate", required = false) LocalDate oDate,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        if(oIdx != null && oDate != null) {
            return new ResponseEntity(storeService.findOpeningStores(dIdx, oIdx, oDate, pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity(storeService.findByIdxDeliverySite(dIdx, pageable), HttpStatus.OK);
        }
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
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @RequestParam(value = "oIdx", required = false) Long oIdx,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(value = "oDate", required = false) LocalDate oDate
    ) {
        if(oIdx != null && oDate != null) {
            return new ResponseEntity(storeService.countOpeningStores(dIdx, oIdx, oDate), HttpStatus.OK);
        } else {
            return new ResponseEntity(storeService.count(), HttpStatus.OK);
        }
    }
}
