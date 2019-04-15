package com.mrporter.pomangam.orderEntry.orderTime.controller;

import com.mrporter.pomangam.orderEntry.orderTime.service.OrderTimeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orderTimes")
@RestController
@AllArgsConstructor
public class OrderTimeController {

    OrderTimeServiceImpl orderTimeService;

    @GetMapping("/search/getBlahBlah")
    public ResponseEntity<?> getBlahBlah(@RequestParam("blah") String blah) {
        return new ResponseEntity(blah, HttpStatus.OK);
    }
}
