package com.mrporter.pomangam.productEntry.product.controller;

import com.mrporter.pomangam.productEntry.product.domain.ProductWithCostDto;
import com.mrporter.pomangam.productEntry.product.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/products")
@RestController
@AllArgsConstructor
public class ProductController {

    ProductServiceImpl productService;

    @GetMapping("/search/findByStoreIdx")
    public ResponseEntity<?> findByStoreIdx(@RequestParam(value = "storeIdx") Integer store_idx,
                                            @RequestParam(value = "type", required = false) Integer type,
                                            @RequestParam(value = "orderBy", required = false) String orderBy) {
        List<ProductWithCostDto> dtoList =  productService.findByStoreIdx(store_idx, type, orderBy);
        if(dtoList == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(dtoList, HttpStatus.OK);
        }
    }

    @GetMapping("/search/findByProductIdx")
    public ResponseEntity<?> findByProductIdx(@RequestParam("productIdx") Integer product_idx) {
        ProductWithCostDto dto = productService.findByProductIdx(product_idx);
        if(dto == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(dto, HttpStatus.OK);
        }
    }
}
