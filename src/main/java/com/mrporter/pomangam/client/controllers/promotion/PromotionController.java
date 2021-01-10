package com.mrporter.pomangam.client.controllers.promotion;

import com.mrporter.pomangam.client.services.promotion.PromotionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dsites/{dIdx}/promotions")
@AllArgsConstructor
public class PromotionController {

    PromotionServiceImpl promotionService;

    @GetMapping
    public ResponseEntity<?> findByIdxDeliverySite(
            @PathVariable(value = "dIdx", required = true) Long dIdx
    ) {

        return new ResponseEntity(promotionService.findByIdxDeliverySite(dIdx), HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> findByIdx(@PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
