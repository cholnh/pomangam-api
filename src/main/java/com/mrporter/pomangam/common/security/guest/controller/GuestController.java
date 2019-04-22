package com.mrporter.pomangam.common.security.guest.controller;

import com.mrporter.pomangam.common.security.guest.service.GuestServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/guest")
@RestController
@AllArgsConstructor
public class GuestController {

    GuestServiceImpl guestService;

    @PostMapping()
    public ResponseEntity<?> post() {
        return new ResponseEntity(guestService.generateGuest(), HttpStatus.OK);
    }

}
