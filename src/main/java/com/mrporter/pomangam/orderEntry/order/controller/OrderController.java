package com.mrporter.pomangam.orderEntry.order.controller;

import com.mrporter.pomangam.orderEntry.order.domain.OrderInfoDto;
import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;
import com.mrporter.pomangam.orderEntry.order.service.OrderServiceImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequestMapping("/orders")
@RestController
@AllArgsConstructor
public class OrderController {

    OrderServiceImpl orderService;

    @GetMapping("/{id}/fail")
    public ResponseEntity<?> fail(@PathVariable(name = "id") Integer order_idx) {
        orderService.setState(order_idx, StateOrder.ORDER_FAIL);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and !hasRole('ROLE_GUEST')")
    @GetMapping("/search/myCurrentInfo")
    public ResponseEntity<?> myCurrentInfo(Principal principal, PageRequest pageRequest) {
        List<OrderInfoDto> orderInfo = orderService.getCurrentOrderInfoByCustomerId(principal.getName(), pageRequest);
        return new ResponseEntity(orderInfo, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_GUEST')")
    @GetMapping("/search/guestCurrentInfo")
    public ResponseEntity<?> guestCurrentInfo(@RequestParam(name = "guestIdx") Integer guestIdx,
                                       PageRequest pageRequest) {
        List<OrderInfoDto> orderInfo = orderService.getCurrentOrderInfoByGuestIdx(guestIdx, pageRequest);
        return new ResponseEntity(orderInfo, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and !hasRole('ROLE_GUEST')")
    @GetMapping("/search/myPastInfo")
    public ResponseEntity<?> myPastInfo(Principal principal, PageRequest pageRequest) {
        List<OrderInfoDto> orderInfo = orderService.getPastOrderInfoByCustomerId(principal.getName(), pageRequest);
        return new ResponseEntity(orderInfo, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_GUEST')")
    @GetMapping("/search/guestPastInfo")
    public ResponseEntity<?> guestPastInfo(@RequestParam(name = "guestIdx") Integer guestIdx,
                                       PageRequest pageRequest) {
        List<OrderInfoDto> orderInfo = orderService.getPastOrderInfoByGuestIdx(guestIdx, pageRequest);
        return new ResponseEntity(orderInfo, HttpStatus.OK);
    }
}
