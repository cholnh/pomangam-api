package com.mrporter.pomangam.client.controllers.ordertime;

import com.mrporter.pomangam.client.services.ordertime.OrderTimeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderTimeController {

    OrderTimeServiceImpl orderTimeService;

    @GetMapping("/dsites/{dIdx}/ordertimes")
    public ResponseEntity<?> findByIdxDeliverySite(
            @PathVariable(value = "dIdx", required = true) Long dIdx
    ) {
        return new ResponseEntity(orderTimeService.findByIdxDeliverySite(dIdx), HttpStatus.OK);
    }

    @GetMapping("/stores/{sIdx}/ordertimes")
    public ResponseEntity<?> findByIdxDeliverySiteAndIdxStore(
            @PathVariable(value = "sIdx", required = true) Long sIdx
    ) {
        return new ResponseEntity(orderTimeService.findByIdxStore(sIdx), HttpStatus.OK);
    }
}