package com.mrporter.pomangam.store.controllers.order;

import com.mrporter.pomangam.client.domains.order.OrderRequestDto;
import com.mrporter.pomangam.client.domains.order.OrderResponseDto;
import com.mrporter.pomangam.client.domains.order.orderer.OrdererType;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.services.order.OrderServiceImpl;
import com.mrporter.pomangam.client.services.order.exception.OrderException;
import com.mrporter.pomangam.store.services._bases.StoreAuthenticationServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class StoreOrderController {

    StoreAuthenticationServiceImpl authenticationService;
    OrderServiceImpl orderService;

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_STORE_OWNER', 'ROLE_ADMIN', 'ROLE_STAFF'))")
    @GetMapping("/store/{sIdx}/orders")
    public ResponseEntity<List<OrderResponseDto>> findAllByIdxStore(
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @RequestParam(value = "dIdx", required = false) Long dIdx,
            @RequestParam(value = "ddIdx", required = false) Long ddIdx,
            @RequestParam(value = "otIdx", required = false) Long otIdx,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(value = "oDate", required = false) LocalDate oDate,
            @RequestParam(value = "last", required = false) Long last,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable,
            Authentication auth
    ) {
        authenticationService.authenticate(auth, sIdx);
        return new ResponseEntity(orderService.findAllByIdxStore(sIdx, dIdx, ddIdx, otIdx, oDate, last, pageable), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_STORE_OWNER', 'ROLE_ADMIN', 'ROLE_STAFF'))")
    @PostMapping("/store/{sIdx}/orders/{oIdx}/approve")
    public ResponseEntity<?> approve(
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "oIdx", required = true) Long oIdx,
            Authentication auth
    ) {
        authenticationService.authenticate(auth, sIdx);
        return new ResponseEntity<>(orderService.approve(oIdx), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_STORE_OWNER', 'ROLE_ADMIN', 'ROLE_STAFF'))")
    @PostMapping("/store/{sIdx}/orders/{oIdx}/disapprove")
    public ResponseEntity<?> disapprove(
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "oIdx", required = true) Long oIdx,
            @RequestParam(value = "reason", required = false) String reason,
            Authentication auth
    ) {
        authenticationService.authenticate(auth, sIdx);
        return new ResponseEntity<>( orderService.disapprove(oIdx, reason), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_STORE_OWNER', 'ROLE_ADMIN', 'ROLE_STAFF'))")
    @PostMapping("/store/{sIdx}/orders/{oIdx}/deliveries/pickup")
    public ResponseEntity<?> deliveryPickup(
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "oIdx", required = true) Long oIdx,
            Authentication auth
    ) {
        authenticationService.authenticate(auth, sIdx);
        return new ResponseEntity<>(orderService.deliveryPickup(oIdx), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_STORE_OWNER', 'ROLE_ADMIN', 'ROLE_STAFF'))")
    @PostMapping("/store/{sIdx}/orders/{oIdx}/deliveries/delay")
    public ResponseEntity<?> deliveryDelay(
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "oIdx", required = true) Long oIdx,
            @RequestParam(value = "min", required = true) Integer min,
            @RequestParam(value = "reason", required = false) String reason,
            Authentication auth
    ) {
        authenticationService.authenticate(auth, sIdx);
        return new ResponseEntity<>( orderService.deliveryDelay(oIdx, min, reason), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_STORE_OWNER', 'ROLE_ADMIN', 'ROLE_STAFF'))")
    @PostMapping("/store/{sIdx}/orders/{oIdx}/deliveries/success")
    public ResponseEntity<?> deliverySuccess(
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "oIdx", required = true) Long oIdx,
            Authentication auth
    ) {
        authenticationService.authenticate(auth, sIdx);
        return new ResponseEntity<>(orderService.deliverySuccess(oIdx), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_STORE_OWNER', 'ROLE_ADMIN', 'ROLE_STAFF'))")
    @PatchMapping("/store/{sIdx}/orders/{oIdx}/note")
    public ResponseEntity<?> patchNote(
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @PathVariable(value = "oIdx", required = true) Long oIdx,
            @RequestParam(value = "note", required = true) String note,
            Authentication auth
    ) {
        authenticationService.authenticate(auth, sIdx);
        return new ResponseEntity<>(orderService.patchNote(oIdx, note), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_STORE_OWNER', 'ROLE_ADMIN', 'ROLE_STAFF'))")
    @PostMapping("/store/{sIdx}/orders")
    public ResponseEntity<OrderResponseDto> customSave(
            @PathVariable(value = "sIdx", required = true) Long sIdx,
            @RequestBody OrderRequestDto orderDto,
            Authentication auth
    ) {
        authenticationService.authenticate(auth, sIdx);

        orderDto.setOrdererType(OrdererType.ADMIN);
        return new ResponseEntity<>(orderService.customSave(orderDto), HttpStatus.OK);
    }
}
