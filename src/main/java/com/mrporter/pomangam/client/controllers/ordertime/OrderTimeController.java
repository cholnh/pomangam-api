package com.mrporter.pomangam.client.controllers.ordertime;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class OrderTimeController {

    @GetMapping("/dsites/{dIdx}/ordertimes")
    public ResponseEntity<?> findByIdxDeliverySite(
            @PathVariable(value = "dIdx", required = true) Long dIdx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/dsites/{dIdx}/ordertimes/{idx}")
    public ResponseEntity<?> findByIdxAndIdxDeliverySite(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/dsites/{dIdx}/stores/{sIdx}/ordertimes")
    public ResponseEntity<?> findByIdxDeliverySiteAndIdxStore(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/dsites/{dIdx}/stores/{sIdx}/ordertimes/{idx}")
    public ResponseEntity<?> findByIdxAndIdxDeliverySiteAndIdxStore(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }
}