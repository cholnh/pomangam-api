package com.mrporter.pomangam.storeEntry.store.controller;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.storeEntry.store.domain.InquiryResultDto;
import com.mrporter.pomangam.storeEntry.store.domain.StoreSummaryDto;
import com.mrporter.pomangam.storeEntry.store.domain.StoreWithCategoryDto;
import com.mrporter.pomangam.storeEntry.store.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/stores")
@RestController
@AllArgsConstructor
public class StoreController {

    StoreService storeService;

    @GetMapping("/search/getInquiryResult")
    public ResponseEntity<?> getInquiryResult(@RequestParam("arrivalDate") String arrival_date,
                                              @RequestParam("detailForDeliverySiteIdx") Integer detail_for_delivery_site_idx) {
        List<InquiryResultDto> inquiryResultDtoList = storeService.getInquiryResult(arrival_date, detail_for_delivery_site_idx);
        if(inquiryResultDtoList == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(inquiryResultDtoList, HttpStatus.OK);
        }
    }

    @GetMapping("/search/findByType")
    public ResponseEntity findByType(@RequestParam("deliverySiteIdx") Integer delivery_site_idx,
                                     @RequestParam("type") Integer type,
                                     @RequestParam(value = "orderBy", required = false) String orderBy,
                                     PageRequest pageRequest) {
        List<StoreSummaryDto> dto = storeService.findByType(delivery_site_idx, type, orderBy, pageRequest);
        if(dto == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(dto, HttpStatus.OK);
        }
    }

    @GetMapping("/search/findWithCategory")
    public ResponseEntity findWithCategory(@RequestParam("storeIdx") Integer storeIdx) {
        StoreWithCategoryDto dto = storeService.findWithCategory(storeIdx);
        if(dto == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(dto, HttpStatus.OK);
        }
    }
}
