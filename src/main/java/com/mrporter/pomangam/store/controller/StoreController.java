package com.mrporter.pomangam.store.controller;

import com.mrporter.pomangam.store.domain.Store;
import com.mrporter.pomangam.store.service.StoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store")
@AllArgsConstructor
public class StoreController {

    StoreServiceImpl orderTimeService;

    @GetMapping
    public ResponseEntity<?> get() {
        List<Store> result = orderTimeService.getStoresByArrivalTimeAndDetailDeliverySite("12:00:00", 1L, 1L);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
