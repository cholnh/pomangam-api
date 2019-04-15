package com.mrporter.pomangam.productEntry.product.controller;

import com.mrporter.pomangam.productEntry.product.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/products")
@RestController
@AllArgsConstructor
public class ProductController {

    ProductServiceImpl productService;

    @GetMapping("/search/getBlahBlah")
    public ResponseEntity<?> getBlahBlah(@RequestParam("blah") String blah) {
        return new ResponseEntity(blah, HttpStatus.OK);
    }
}
