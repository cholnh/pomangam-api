package com.mrporter.pomangam.customerService.faq.controller;

import com.mrporter.pomangam.customerService.faq.service.FaqServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/faq")
@RestController
@AllArgsConstructor
public class FaqController {

    FaqServiceImpl policyService;

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity(policyService.getAll(), HttpStatus.OK);
    }
}
