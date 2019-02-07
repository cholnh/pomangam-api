package com.mrporter.pomangam.store.controller;

import com.mrporter.pomangam.store.domain.StoreJoinOrderTimeDto;
import com.mrporter.pomangam.store.service.StoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/store")
@AllArgsConstructor
public class StoreController {

    StoreServiceImpl storeService;

    @GetMapping
    public ResponseEntity<?> get() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 12);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        List<StoreJoinOrderTimeDto> result = storeService.getStoresByArrivalTimeAndDeliverySite(c.getTime(), 1);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
