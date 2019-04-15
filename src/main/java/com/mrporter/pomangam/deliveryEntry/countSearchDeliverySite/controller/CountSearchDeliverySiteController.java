package com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.controller;

import com.mrporter.pomangam.deliveryEntry.countSearchDeliverySite.service.CountSearchDeliverySiteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/countSearchDeliverySites")
@RestController
@AllArgsConstructor
public class CountSearchDeliverySiteController {

    CountSearchDeliverySiteServiceImpl countSearchDeliverySiteService;

    @GetMapping("/addCount")
    public ResponseEntity<?> addCount(@RequestParam("deliverySiteIdx") Integer deliverySiteIdx) {
        countSearchDeliverySiteService.addCount(deliverySiteIdx);
        return new ResponseEntity( HttpStatus.OK);
    }
}
