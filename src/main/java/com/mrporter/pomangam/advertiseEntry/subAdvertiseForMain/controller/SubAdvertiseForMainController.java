package com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.controller;

import com.mrporter.pomangam.advertiseEntry.subAdvertiseForMain.service.SubAdvertiseForMainServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subAdvertises/main")
@AllArgsConstructor
public class SubAdvertiseForMainController {

    SubAdvertiseForMainServiceImpl subAdvertiseForMainService;

    @GetMapping
    public ResponseEntity<?> getAdvertise(@RequestParam(value = "deliverySiteIdx", required = true) Integer delivery_site_idx) {
        return new ResponseEntity<>(subAdvertiseForMainService.getSubAdvertiseMainsByDeliverySiteIdx(delivery_site_idx), HttpStatus.OK);
    }

}
