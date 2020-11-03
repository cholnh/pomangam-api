package com.mrporter.pomangam.store.controllers.ordertime;

import com.mrporter.pomangam.client.services.ordertime.OrderTimeServiceImpl;
import com.mrporter.pomangam.store.services._bases.StoreAuthenticationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class StoreOrderTimeController {

    StoreAuthenticationServiceImpl authenticationService;
    OrderTimeServiceImpl orderTimeService;

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_STORE_OWNER', 'ROLE_ADMIN', 'ROLE_STAFF'))")
    @GetMapping("/store/{sIdx}/ordertimes")
    public ResponseEntity<?> findByIdxStore(
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @RequestParam(value = "dIdx", required = false) Long dIdx,
            Authentication auth
    ) {
        if(authenticationService.isStoreOwner(auth, sIdx)) {
            if(dIdx == null) {
                return new ResponseEntity(orderTimeService.findByIdxStore(sIdx), HttpStatus.OK);
            } else {
                return new ResponseEntity(orderTimeService.findByIdxDeliverySite(dIdx), HttpStatus.OK);
            }

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}