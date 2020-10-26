package com.mrporter.pomangam.client.controllers.kakao;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kakaos")
@AllArgsConstructor
public class KakaoController {

    private static final Logger logger = LoggerFactory.getLogger(KakaoController.class);

    @GetMapping("/callback")
    public ResponseEntity<?> get(
            @RequestBody String data
    ) {
        System.out.println("get data: " + data);
        logger.info("get data: " + data);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/callback")
    public ResponseEntity<?> post(
            @RequestBody String data
    ) {
        System.out.println("post data: " + data);
        logger.info("post data: " + data);
        return new ResponseEntity(HttpStatus.OK);
    }
}
