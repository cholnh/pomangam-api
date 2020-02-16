package com.mrporter.pomangam.client.controllers.product.subs;

import com.mrporter.pomangam.client.services.product.subs.ProductSubServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dsites/{didx}/stores/{sidx}/products/{pidx}/subs")
@AllArgsConstructor
public class ProductSubController {

    ProductSubServiceImpl productSubService;

    @GetMapping
    public ResponseEntity<?> getByIdxProduct(
            @PathVariable(value = "didx", required = true) Long didx,
            @PathVariable(value = "sidx", required = true) Long sidx,
            @PathVariable(value = "pidx", required = true) Long pidx
    ) {
        return new ResponseEntity(productSubService.getByIdxProduct(pidx), HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getByIdx(
            @PathVariable(value = "didx", required = true) Long didx,
            @PathVariable(value = "sidx", required = true) Long sidx,
            @PathVariable(value = "pidx", required = true) Long pidx,
            @PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(productSubService.getByIdx(idx), HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> searchCount(
            @PathVariable(value = "didx", required = true) Long didx,
            @PathVariable(value = "sidx", required = true) Long sidx,
            @PathVariable(value = "pidx", required = true) Long pidx
    ) {
        return new ResponseEntity(productSubService.count(), HttpStatus.OK);
    }
}
