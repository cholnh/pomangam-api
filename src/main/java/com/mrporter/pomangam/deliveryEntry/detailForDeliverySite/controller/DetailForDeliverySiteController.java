package com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.controller;

import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain.DetailForDeliverySiteDto;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.service.DetailForDeliverySiteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/detailForDeliverySites")
@RestController
@AllArgsConstructor
public class DetailForDeliverySiteController {

    DetailForDeliverySiteServiceImpl detailForDeliverySiteService;

    @GetMapping("/search/findByDeliverySiteIdxOrderBySequence")
    public ResponseEntity<?> findByDeliverySiteIdxOrderBySequence(@RequestParam("deliverySiteIdx") Integer delivery_site_idx) {
        List<DetailForDeliverySiteDto> detailForDeliverySiteDtoList = detailForDeliverySiteService.findByDeliverySiteIdxOrderBySequence(delivery_site_idx);
        if(detailForDeliverySiteDtoList == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(detailForDeliverySiteDtoList, HttpStatus.OK);
        }
    }
}
