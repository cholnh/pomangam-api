package com.mrporter.pomangam.client.controllers.deliverysite.detail;

import com.mrporter.pomangam.client.services.deliverysite.detail.DeliveryDetailSiteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dsites/{didx}/details")
@AllArgsConstructor
public class DeliveryDetailSiteController {

    DeliveryDetailSiteServiceImpl detailSiteService;

    @GetMapping
    public ResponseEntity<?> get(
            @PathVariable(value = "didx", required = true) Integer didx
    ) {
        return new ResponseEntity(detailSiteService.getByIdxDeliverySite(didx), HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getByIdx(
            @PathVariable(value = "didx", required = true) Integer didx,
            @PathVariable(value = "idx", required = true) Integer idx
    ) {
        return new ResponseEntity(detailSiteService.getByIdx(idx), HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> searchCount(
            @PathVariable(value = "didx", required = true) Integer didx
    ) {
        return new ResponseEntity(detailSiteService.count(), HttpStatus.OK);
    }

}
