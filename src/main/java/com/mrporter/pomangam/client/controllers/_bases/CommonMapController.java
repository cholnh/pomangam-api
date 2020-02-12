package com.mrporter.pomangam.client.controllers._bases;

import com.mrporter.pomangam.client.domains._bases.CommonMap;
import com.mrporter.pomangam.client.services._bases.CommonMapServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maps")
@AllArgsConstructor
public class CommonMapController {

    CommonMapServiceImpl commonMapService;

    @GetMapping("/{key}")
    public ResponseEntity<?> getValue(@PathVariable(value = "key", required = true) String key) {
        List<CommonMap> dto = commonMapService.getValue(key);
        if(dto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

}
