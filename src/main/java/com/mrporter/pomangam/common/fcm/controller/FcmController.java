package com.mrporter.pomangam.common.fcm.controller;

import com.mrporter.pomangam.common.fcm.domain.FcmToken;
import com.mrporter.pomangam.common.fcm.service.FcmServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fcm")
@AllArgsConstructor
public class FcmController {

    FcmServiceImpl fcmService;

    @PostMapping
    public ResponseEntity<?> addToken(@RequestBody FcmToken token) {
        fcmService.addToken(token);
        return ResponseEntity.ok(null);
    }
}
