package com.mrporter.pomangam.deliveryEntry.deliverySite.controller;

import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySiteDto;
import com.mrporter.pomangam.deliveryEntry.deliverySite.service.DeliverySiteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/deliverySites")
@RestController
@AllArgsConstructor
public class DeliverySiteController {

    DeliverySiteServiceImpl deliverySiteService;

    @GetMapping("/search/getByDeliverySiteIdx")
    public ResponseEntity<?> getByDeliverySiteIdx(@RequestParam("deliverySiteIdx") Integer deliverySiteIdx) {
        DeliverySiteDto dto = deliverySiteService.getByDeliverySiteIdx(deliverySiteIdx);
        if(dto == null) {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity(dto, HttpStatus.OK);
        }
    }

    @GetMapping("/search/findByQuery")
    public ResponseEntity<?> findByQuery(@RequestParam("query") String query) {
        List<DeliverySiteDto> deliverySiteDtoList = deliverySiteService.findByQuery(query);
        if(deliverySiteDtoList == null) {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity(deliverySiteDtoList, HttpStatus.OK);
        }
    }

    @GetMapping("/search/findByRegionCategoryIdx")
    public ResponseEntity<?> findByRegionCategoryIdx(@RequestParam("regionCategoryIdx") Integer regionCategoryIdx) {
        List<DeliverySiteDto> deliverySiteDtoList = deliverySiteService.findByRegionCategoryIdx(regionCategoryIdx);
        if(deliverySiteDtoList == null) {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity(deliverySiteDtoList, HttpStatus.OK);
        }
    }
}
