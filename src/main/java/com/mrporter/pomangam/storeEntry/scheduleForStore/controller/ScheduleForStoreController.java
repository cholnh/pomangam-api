package com.mrporter.pomangam.storeEntry.scheduleForStore.controller;

import com.mrporter.pomangam.storeEntry.scheduleForStore.service.ScheduleForStoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/scheduleForStores")
@RestController
@AllArgsConstructor
public class ScheduleForStoreController {

    ScheduleForStoreServiceImpl scheduleForStoreService;

    @GetMapping("/search/getBlahBlah")
    public ResponseEntity<?> getBlahBlah(@RequestParam("blah") String blah) {
        return new ResponseEntity(blah, HttpStatus.OK);
    }
}
