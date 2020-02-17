package com.mrporter.pomangam._bases.securities.oauth2clientdetail.controller;

import com.mrporter.pomangam._bases.securities.oauth2clientdetail.service.Oauth2ClientDetailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/oauth2ClientDetails")
@RestController
@AllArgsConstructor
public class Oauth2ClientDetailController {

    Oauth2ClientDetailServiceImpl countSearchDeliverySiteService;

    @GetMapping()
    public ResponseEntity<?> get() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
