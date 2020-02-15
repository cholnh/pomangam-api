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

    @GetMapping("/dsites/{didx}/ordertimes")
    public ResponseEntity<?> getByIdxDeliverySite(
            @PathVariable(value = "didx", required = true) Long didx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/dsites/{didx}/ordertimes/{idx}")
    public ResponseEntity<?> getByIdxAndIdxDeliverySite(
            @PathVariable(value = "didx", required = true) Long didx,
            @PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/dsites/{didx}/stores/{sidx}/ordertimes")
    public ResponseEntity<?> getByIdxDeliverySiteAndIdxStore(
            @PathVariable(value = "didx", required = true) Long didx,
            @PathVariable(value = "sidx", required = true) Long sidx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/dsites/{didx}/stores/{sidx}/ordertimes/{idx}")
    public ResponseEntity<?> getByIdxAndIdxDeliverySiteAndIdxStore(
            @PathVariable(value = "didx", required = true) Long didx,
            @PathVariable(value = "sidx", required = true) Long sidx,
            @PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }
}