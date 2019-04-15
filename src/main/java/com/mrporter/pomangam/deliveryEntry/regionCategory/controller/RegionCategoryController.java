package com.mrporter.pomangam.deliveryEntry.regionCategory.controller;

import com.mrporter.pomangam.deliveryEntry.regionCategory.service.RegionCategoryServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/regionCategorys")
@RestController
@AllArgsConstructor
public class RegionCategoryController {

    RegionCategoryServiceImpl regionCategoryService;

    @GetMapping("/search/getBlahBlah")
    public ResponseEntity<?> getBlahBlah(@RequestParam("blah") String blah) {
        return new ResponseEntity(blah, HttpStatus.OK);
    }
}
