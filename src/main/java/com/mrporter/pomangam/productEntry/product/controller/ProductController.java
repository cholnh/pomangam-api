package com.mrporter.pomangam.productEntry.product.controller;

import com.mrporter.pomangam.productEntry.product.domain.ProductWithCostDto;
import com.mrporter.pomangam.productEntry.product.service.ProductServiceImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/products")
@RestController
@AllArgsConstructor
public class ProductController {

    ProductServiceImpl productService;

    @GetMapping("/search/findByStoreIdx")
    public ResponseEntity<?> findByStoreIdx(@RequestParam("storeIdx") Integer store_idx,
                                            @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                            @RequestParam(value = "type", required = false) Integer type,
                                            @RequestParam(value = "orderBy", required = false) String orderBy,
                                            PageRequest pageRequest) {
        List<ProductWithCostDto> dtoList;
        if(categoryId == null) {
            dtoList =  productService.findByStoreIdx(store_idx, type, orderBy, pageRequest);
        } else {
            dtoList =  productService.findByCategoryId(store_idx, categoryId, type, orderBy, pageRequest);
        }
        if(dtoList == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(dtoList, HttpStatus.OK);
        }
    }

    @GetMapping("/search/findByProductIdx")
    public ResponseEntity<?> findByProductIdx(@RequestParam("productIdx") Integer product_idx,
                                              Principal principal) {
        ProductWithCostDto dto = productService.findByProductIdx(product_idx, principal.getName());
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

    @GetMapping("/{productIdx}/like")
    public ResponseEntity like(@PathVariable(name = "productIdx") Integer productIdx,
                               Principal principal) {
        productService.like(productIdx, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{productIdx}/unlike")
    public ResponseEntity unlike(@PathVariable(name = "productIdx") Integer productIdx,
                               Principal principal) {
        productService.unlike(productIdx, principal.getName());
        return new ResponseEntity(HttpStatus.OK);
    }
}
