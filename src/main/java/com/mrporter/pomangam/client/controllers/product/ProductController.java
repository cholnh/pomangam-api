package com.mrporter.pomangam.client.controllers.product;

import com.mrporter.pomangam.client.services.product.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dsites/{dIdx}/stores/{sIdx}/products")
@AllArgsConstructor
public class ProductController {

    ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<?> findByIdxStore(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @RequestParam(value = "cIdx", required = false) Long cIdx,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        if(cIdx == null) {
            return new ResponseEntity(productService.findSummaryByIdxStore(sIdx, pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity(productService.findSummaryByIdxProductCategory(cIdx, pageable), HttpStatus.OK);
        }
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> findByIdx(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "idx", required = true) Long idx,
            Authentication auth
    ) {
        String phoneNumber = null;
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            phoneNumber = auth.getName();
        }
        return new ResponseEntity(productService.findByIdx(idx, phoneNumber), HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> count(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @RequestParam(value = "cIdx", required = false) Long cIdx
    ) {
        if(cIdx == null) {
            return new ResponseEntity(productService.countByIdxStore(sIdx), HttpStatus.OK);
        } else {
            return new ResponseEntity(productService.countByIdxProductCategory(cIdx), HttpStatus.OK);
        }
    }
}
