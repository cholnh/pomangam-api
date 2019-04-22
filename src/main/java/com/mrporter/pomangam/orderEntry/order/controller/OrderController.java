package com.mrporter.pomangam.orderEntry.order.controller;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.util.security.SessionConverter;
import com.mrporter.pomangam.orderEntry.order.domain.OrderInfoDto;
import com.mrporter.pomangam.orderEntry.order.domain.OrderInputDto;
import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;
import com.mrporter.pomangam.orderEntry.order.service.OrderServiceImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/orders")
@RestController
@AllArgsConstructor
public class OrderController {

    OrderServiceImpl orderService;

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody OrderInputDto order) {
        //orderService.saveOrderInput(order);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}/fail")
    public ResponseEntity<?> fail(@PathVariable(name = "id") Integer order_idx) {
        orderService.setState(order_idx, StateOrder.ORDER_FAIL);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/myCurrentInfo")
    public ResponseEntity<?> myCurrentInfo(HttpSession session,
                                    PageRequest pageRequest) {
        User user = SessionConverter.getCustomer(session);
        Authentication authentication = SessionConverter.getAuthentication(session);
        if(authentication == null || user == null) {
            return new ResponseEntity("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
        }

        List<OrderInfoDto> orderInfo = orderService.getCurrentOrderInfoByCustomerIdx(user.getIdx(), pageRequest);
        return new ResponseEntity(orderInfo, HttpStatus.OK);
    }

    @GetMapping("/search/guestCurrentInfo")
    public ResponseEntity<?> guestCurrentInfo(@RequestParam(name = "guestIdx") Integer guestIdx,
                                       PageRequest pageRequest) {
        List<OrderInfoDto> orderInfo = orderService.getCurrentOrderInfoByGuestIdx(guestIdx, pageRequest);
        return new ResponseEntity(orderInfo, HttpStatus.OK);
    }

    @GetMapping("/search/myPastInfo")
    public ResponseEntity<?> myPastInfo(HttpSession session,
                                           PageRequest pageRequest) {
        User user = SessionConverter.getCustomer(session);
        Authentication authentication = SessionConverter.getAuthentication(session);
        if(authentication == null || user == null) {
            return new ResponseEntity("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
        }

        List<OrderInfoDto> orderInfo = orderService.getPastOrderInfoByCustomerIdx(user.getIdx(), pageRequest);
        return new ResponseEntity(orderInfo, HttpStatus.OK);
    }

    @GetMapping("/search/guestPastInfo")
    public ResponseEntity<?> guestPastInfo(@RequestParam(name = "guestIdx") Integer guestIdx,
                                       PageRequest pageRequest) {
        List<OrderInfoDto> orderInfo = orderService.getPastOrderInfoByGuestIdx(guestIdx, pageRequest);
        return new ResponseEntity(orderInfo, HttpStatus.OK);
    }
}
