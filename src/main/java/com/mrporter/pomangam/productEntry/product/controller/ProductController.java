package com.mrporter.pomangam.productEntry.product.controller;

import com.mrporter.pomangam.productEntry.product.domain.ProductViewDto;
import com.mrporter.pomangam.productEntry.product.domain.ProductWithCostDto;
import com.mrporter.pomangam.productEntry.product.service.ProductServiceImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
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
    public ResponseEntity<?> findByStoreIdx(@RequestParam("storeIdx") Integer store_idx,
                                            @RequestParam(value = "type", required = false) Integer type,
                                            @RequestParam(value = "orderBy", required = false) String orderBy,
                                            PageRequest pageRequest) {
        List<ProductWithCostDto> dtoList =  productService.findByStoreIdx(store_idx, type, orderBy, pageRequest);
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

    @GetMapping("/search/findByQuery")
    public ResponseEntity<?> findByQuery(@RequestParam("query") String query,
                                         @RequestParam("deliverySiteIdx") Integer delivery_site_idx) {
            return new ResponseEntity(productService.findByQuery(query, delivery_site_idx), HttpStatus.OK);
    }

    @GetMapping("/search/getDetailOrder")
    public ResponseEntity<?> getDetailOrder(@RequestParam("productIdx") Integer productIdx) {
        return new ResponseEntity(productService.getDetailOrder(productIdx), HttpStatus.OK);
    }

    @GetMapping("/search/findWithCategoryByStoreIdx")
    public ResponseEntity<?> findWithCategoryByStoreIdx(@RequestParam("storeIdx") Integer store_idx,
                                            @RequestParam(value = "type", required = false) Integer type,
                                            @RequestParam(value = "orderBy", required = false) String orderBy,
                                            PageRequest pageRequest) {
        ProductViewDto productViewDto =  productService.findWithCategoryByStoreIdx(store_idx, type, orderBy, pageRequest);
        if(productViewDto == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(productViewDto, HttpStatus.OK);
        }
    }

}
