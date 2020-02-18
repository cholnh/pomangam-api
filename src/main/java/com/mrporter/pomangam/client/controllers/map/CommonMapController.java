package com.mrporter.pomangam.client.controllers.map;

import com.mrporter.pomangam.client.domains.map.CommonMap;
import com.mrporter.pomangam.client.services.map.CommonMapServiceImpl;
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
    public ResponseEntity<?> findByKey(
            @PathVariable(value = "key", required = true) String key
    ) {
        List<CommonMap> dto = commonMapService.findAllByKey(key);
        if(dto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

}
