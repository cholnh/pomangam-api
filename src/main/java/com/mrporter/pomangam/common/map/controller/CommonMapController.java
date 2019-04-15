package com.mrporter.pomangam.common.map.controller;

import com.mrporter.pomangam.common.map.domain.CommonMap;
import com.mrporter.pomangam.common.map.service.CommonMapServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/maps")
@AllArgsConstructor
public class CommonMapController {

    CommonMapServiceImpl commonMapService;

    @GetMapping("/search/getValue")
    public ResponseEntity<?> getValue(@RequestParam("key") String key) {
        List<CommonMap> dto = commonMapService.getValue(key);
        if(dto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }

    }

}
