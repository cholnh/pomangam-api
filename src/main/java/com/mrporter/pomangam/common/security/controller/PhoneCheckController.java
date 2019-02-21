package com.mrporter.pomangam.common.security.controller;

import com.mrporter.pomangam.common.security.service.PhoneCheckServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/check/phoneNumber")
@RestController
@AllArgsConstructor
public class PhoneCheckController {

    PhoneCheckServiceImpl phoneCheckService;

    @GetMapping
    public ResponseEntity<?> checkPhoneNumber(@RequestParam("phn") String phone_number) {
        String auth_code = phoneCheckService.getAuthCode();
        ResponseEntity<?> sendResult = phoneCheckService.sendAuthCode(phone_number, auth_code);
        return new ResponseEntity<>(auth_code, HttpStatus.OK);
    }
}
