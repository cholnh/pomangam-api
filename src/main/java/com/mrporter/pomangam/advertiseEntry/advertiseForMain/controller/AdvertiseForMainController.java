package com.mrporter.pomangam.advertiseEntry.advertiseForMain.controller;

import com.mrporter.pomangam.advertiseEntry.advertiseForMain.service.AdvertiseForMainServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advertises/main")
@AllArgsConstructor
public class AdvertiseForMainController {

    AdvertiseForMainServiceImpl advertiseForMainService;

    @GetMapping
    public ResponseEntity<?> getAdvertise(@RequestParam(value = "deliverySiteIdx", required = true) Integer delivery_site_idx) {
        return new ResponseEntity<>(advertiseForMainService.getAdvertiseMainsByDeliverySiteIdx(delivery_site_idx), HttpStatus.OK);
    }

}
