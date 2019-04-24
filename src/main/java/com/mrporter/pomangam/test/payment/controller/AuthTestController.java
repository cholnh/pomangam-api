package com.mrporter.pomangam.test.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.security.Principal;

@RestController
@RequestMapping("/tests/auth")
@Slf4j
public class AuthTestController {
    public static void main(String[] args) {
    }

    @PermitAll  // not working
    @GetMapping("/0")
    public ResponseEntity basic0(Principal principal, Authentication authentication) {
        String test = " s f ";
        log.info("로그 테스트 '{}'", test);
        System.out.println("principal : "+principal);
        System.out.println("authentication : "+authentication);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/1")
    public ResponseEntity basic1(Principal principal, Authentication authentication) {
        System.out.println("principal : "+principal);
        System.out.println("authentication : "+authentication);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostAuthorize("isAuthenticated()")
    @GetMapping("/2")
    public ResponseEntity basic2(Principal principal, Authentication authentication) {

        System.out.println("principal : "+principal);
        System.out.println("authentication : "+authentication);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @GetMapping("/3")
    public ResponseEntity basic3(Principal principal, Authentication authentication) {
        System.out.println("principal : "+principal);
        System.out.println("authentication : "+authentication);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostAuthorize("isAuthenticated() and hasRole('ROLE_USER')")
    @GetMapping("/4")
    public ResponseEntity basic4(Principal principal, Authentication authentication) {
        System.out.println("principal : "+principal);
        System.out.println("authentication : "+authentication);
        return new ResponseEntity(HttpStatus.OK);
    }
}
