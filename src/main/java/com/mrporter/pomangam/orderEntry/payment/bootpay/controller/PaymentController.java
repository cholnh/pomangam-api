package com.mrporter.pomangam.orderEntry.payment.bootpay.controller;

import com.mrporter.pomangam.orderEntry.order.domain.OrderInfoDto;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.*;
import com.mrporter.pomangam.orderEntry.payment.bootpay.domain.request.Cancel;
import com.mrporter.pomangam.orderEntry.payment.bootpay.service.PaymentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/payments")
@RestController
@AllArgsConstructor
@Slf4j
public class PaymentController {
    private static final String BOOTPAY_ROUTER_GATEWAY_IP = "223.130.82.4";

    PaymentServiceImpl paymentService;

    @PostMapping("/prepare")
    public ResponseEntity<?> prepare(@RequestBody PaymentInputDto dto) {
        PaymentOutputDto result = paymentService.prepare(dto);
        if(result == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping("/complete")
    public ResponseEntity<?> complete(@RequestBody CompleteInputDto dto) {
        OrderInfoDto result = paymentService.complete(dto);
        if(result == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/cancel")
    public ResponseEntity cancel(@RequestBody Cancel cancel) {
        CancelDto result = paymentService.cancel(cancel);
        if(result == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }

    @PostMapping("/fail")
    public ResponseEntity fail(@RequestBody FailInputDto dto) {
        paymentService.fail(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/feedback")
    public ResponseEntity<?> feedback(@RequestBody FeedbackDto dto,
                                      HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        ip = ip == null ? request.getRemoteAddr() : ip;
        if(ip == null || !ip.equals(BOOTPAY_ROUTER_GATEWAY_IP)) {
            log.warn("BOOTPAY_ROUTER_GATEWAY_IP IS INVALID");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(dto == null || !dto.getPrivate_key().equals(PaymentServiceImpl.getPrivate_key())) {
            log.warn("PRIVATE_KEY IS INVALID");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(dto);



        // Todo 가상계좌 결과 입력받기
        // 취소 결과 입력받기

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
