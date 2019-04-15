package com.mrporter.pomangam.promotionEntry.promotion.controller;

import com.mrporter.pomangam.promotionEntry.promotion.repository.PromotionRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/promotions")
@RestController
@AllArgsConstructor
public class PromotionController {

    PromotionRepositoryImpl promotionRepository;

    @GetMapping("/search/getBlahBlah")
    public ResponseEntity<?> getBlahBlah(@RequestParam("blah") String blah) {
        return new ResponseEntity(blah, HttpStatus.OK);
    }
}
