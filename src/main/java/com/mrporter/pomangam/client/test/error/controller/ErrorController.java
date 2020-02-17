package com.mrporter.pomangam.client.test.error.controller;

import com.mrporter.pomangam.client.test.error.domain.DummyData;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/errors")
@AllArgsConstructor
public class ErrorController {

    @GetMapping("/200")
    public ResponseEntity<?> get200(
            @RequestHeader(value="Accept-Language", required = false) String acceptLanguage
    ) {
        System.out.println(acceptLanguage);
        DummyData dummyData = new DummyData(1, "OK test key", "test val");
        return new ResponseEntity(dummyData, HttpStatus.OK);
    }

    @GetMapping("/400")
    public ResponseEntity<?> get400() {
        DummyData dummyData = new DummyData(1, "BAD_REQUEST test key", "test val");
        return new ResponseEntity(dummyData, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/500")
    public ResponseEntity<?> get500() {
        DummyData dummyData = new DummyData(1, "INTERNAL_SERVER_ERROR test key", "test val");
        return new ResponseEntity(dummyData, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
