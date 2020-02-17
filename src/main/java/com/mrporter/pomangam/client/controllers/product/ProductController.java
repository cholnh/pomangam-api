package com.mrporter.pomangam.client.controllers.product;

import com.mrporter.pomangam.client.services.product.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dsites/{dIdx}/stores/{sIdx}/products")
@AllArgsConstructor
public class ProductController {

    ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<?> findByIdxStore(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(productService.findByIdxStore(sIdx, pageable), HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> findByIdx(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(productService.findByIdx(idx), HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> count(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx
    ) {
        return new ResponseEntity(productService.count(), HttpStatus.OK);
    }
}
