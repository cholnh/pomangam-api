package com.mrporter.pomangam.store.controllers.store;

import com.mrporter.pomangam.client.domains.store.StoreDto;
import com.mrporter.pomangam.client.services.store.ClientStoreServiceImpl;
import com.mrporter.pomangam.store.services._bases.StoreAuthenticationServiceImpl;
import com.mrporter.pomangam.store.services.store.StoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
public class StoreController {

    StoreAuthenticationServiceImpl authenticationService;
    StoreServiceImpl storeService;

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_STORE_OWNER', 'ROLE_ADMIN', 'ROLE_STAFF'))")
    @GetMapping("/store/{sIdx}")
    public ResponseEntity<?> findByIdx(
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            Authentication auth
    ) {
        authenticationService.authenticate(auth, sIdx);
        return new ResponseEntity(storeService.findByIdx(sIdx), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_STORE_OWNER', 'ROLE_ADMIN', 'ROLE_STAFF'))")
    @PatchMapping("/store/{sIdx}")
    public ResponseEntity<?> patch(
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @RequestBody(required = true) StoreDto dto,
            Authentication auth
    ) {
        authenticationService.authenticate(auth, sIdx);
        dto.setIdx(sIdx);
        return new ResponseEntity(storeService.patch(dto), HttpStatus.OK);
    }

}
