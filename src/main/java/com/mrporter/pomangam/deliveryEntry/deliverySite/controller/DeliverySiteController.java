package com.mrporter.pomangam.deliveryEntry.deliverySite.controller;

import com.mrporter.pomangam.deliveryEntry.deliverySite.service.DeliverySiteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/deliverySites")
@RestController
@AllArgsConstructor
public class DeliverySiteController {

    DeliverySiteServiceImpl deliverySiteService;

    @GetMapping("/search/getBlahBlah")
    public ResponseEntity<?> getBlahBlah(@RequestParam("blah") String blah) {
        return new ResponseEntity(blah, HttpStatus.OK);
    }
}
