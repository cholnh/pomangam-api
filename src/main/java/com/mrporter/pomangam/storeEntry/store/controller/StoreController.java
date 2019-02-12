package com.mrporter.pomangam.storeEntry.store.controller;

import com.mrporter.pomangam.storeEntry.store.domain.InquiryResultDto;
import com.mrporter.pomangam.storeEntry.store.service.StoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/store")
@AllArgsConstructor
public class StoreController {

    StoreServiceImpl storeService;

    @GetMapping
    public ResponseEntity<?> get() {
        LocalDateTime testSet = LocalDateTime.of(2019, 2, 9, 13, 0);

        long start = System.currentTimeMillis();
        List<InquiryResultDto> result = storeService.getInquiryResult(testSet, 2);
        System.out.println("time : " + (System.currentTimeMillis() - start));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
