package com.mrporter.pomangam.common.index.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class IndexController {

    @GetMapping
    public ResponseEntity<?> index() {
        return new ResponseEntity<String>("this is home", HttpStatus.OK);
    }
}
