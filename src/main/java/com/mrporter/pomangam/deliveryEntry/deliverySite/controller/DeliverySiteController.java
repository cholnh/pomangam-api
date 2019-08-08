package com.mrporter.pomangam.deliveryEntry.deliverySite.controller;

import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySiteDto;
import com.mrporter.pomangam.deliveryEntry.deliverySite.domain.DeliverySiteWithCountDto;
import com.mrporter.pomangam.deliveryEntry.deliverySite.service.DeliverySiteServiceImpl;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain.DetailForDeliverySiteDto;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.service.DetailForDeliverySiteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/deliverySites")
@RestController
@AllArgsConstructor
public class DeliverySiteController {

    DeliverySiteServiceImpl deliverySiteService;
    DetailForDeliverySiteServiceImpl detailForDeliverySiteService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity(deliverySiteService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{deliverySiteIdx}")
    public ResponseEntity<?> getBy(@PathVariable(value = "deliverySiteIdx", required = true) Integer deliverySiteIdx) {
        DeliverySiteDto dto = deliverySiteService.getByDeliverySiteIdx(deliverySiteIdx);
        if(dto == null) {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity(dto, HttpStatus.OK);
        }
    }

    @Deprecated
    @GetMapping("/search/getByDeliverySiteIdx")
    public ResponseEntity<?> getByDeliverySiteIdx(@RequestParam(value = "deliverySiteIdx", required = true) Integer deliverySiteIdx) {
        return getBy(deliverySiteIdx);
    }

    @GetMapping("/search/findByQuery")
    public ResponseEntity<?> findByQuery(@RequestParam(value = "query", required = true) String query) {
        List<DeliverySiteWithCountDto> deliverySiteDtoList = deliverySiteService.findByQuery(query);
        if(deliverySiteDtoList == null) {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity(deliverySiteDtoList, HttpStatus.OK);
        }
    }

    @GetMapping("/search/findByRegionCategoryIdx")
    public ResponseEntity<?> findByRegionCategoryIdx(@RequestParam(value = "regionCategoryIdx", required = true) Integer regionCategoryIdx) {
        List<DeliverySiteDto> deliverySiteDtoList = deliverySiteService.findByRegionCategoryIdx(regionCategoryIdx);
        if(deliverySiteDtoList == null) {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity(deliverySiteDtoList, HttpStatus.OK);
        }
    }

    @GetMapping("/{deliverySiteIdx}/detailForDeliverySites")
    public ResponseEntity<?> getAllDetailSite(@PathVariable(value = "deliverySiteIdx", required = true) Integer deliverySiteIdx) {
        List<DetailForDeliverySiteDto> detailForDeliverySiteDtos = detailForDeliverySiteService.findByDeliverySiteIdxOrderBySequence(deliverySiteIdx);
        if(detailForDeliverySiteDtos == null) {
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return  new ResponseEntity(detailForDeliverySiteDtos, HttpStatus.OK);
        }
    }

}
