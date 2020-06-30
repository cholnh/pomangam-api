package com.mrporter.pomangam.store.controllers._bases;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF') or hasRole('ROLE_STORE_OWNER')")
@RestController
@RequestMapping("/store")
@AllArgsConstructor
public class StoreIndexController {

    @GetMapping
    public ResponseEntity<?> index() {
        return new ResponseEntity<>("Hello, Here is Pomangam's [STORE] Home", HttpStatus.OK);
    }
}
