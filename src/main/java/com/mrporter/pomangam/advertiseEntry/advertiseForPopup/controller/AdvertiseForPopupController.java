package com.mrporter.pomangam.advertiseEntry.advertiseForPopup.controller;

import com.mrporter.pomangam.advertiseEntry.advertiseForPopup.service.AdvertiseForPopupServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advertises/popup")
@AllArgsConstructor
public class AdvertiseForPopupController {

    AdvertiseForPopupServiceImpl advertiseForPopupService;

    @GetMapping
    public ResponseEntity<?> getAdvertise(@RequestParam(value = "deliverySiteIdx", required = true) Integer delivery_site_idx) {
        return new ResponseEntity<>(advertiseForPopupService.getAdvertisePopupsByDeliverySiteIdx(delivery_site_idx), HttpStatus.OK);
    }

}
