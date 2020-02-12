package com.mrporter.pomangam.admin.controllers._bases;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class _IndexController {

    @GetMapping
    public ResponseEntity<?> index() {
        return new ResponseEntity<>("Hello, Here is Pomangam's [ADMIN] Home", HttpStatus.OK);
    }
}
