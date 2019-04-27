package com.mrporter.pomangam.orderEntry.payment.controller;

import com.mrporter.pomangam.orderEntry.order.domain.Order;
import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;
import com.mrporter.pomangam.orderEntry.order.service.OrderServiceImpl;
import com.mrporter.pomangam.orderEntry.payment.domain.PaymentInputDto;
import com.mrporter.pomangam.orderEntry.payment.domain.PaymentResultDto;
import com.mrporter.pomangam.orderEntry.payment.service.PaymentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/payments")
@RestController
@AllArgsConstructor
public class PaymentController {

    PaymentServiceImpl paymentService;
    OrderServiceImpl orderService;

    @PostMapping("/prepare")
    public ResponseEntity<?> prepare(@RequestBody PaymentInputDto dto) {
        Order order = paymentService.prepare(dto);
        if(order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/{id}/fail")
    public ResponseEntity<?> fail(@PathVariable(name = "id") Integer order_idx) {
        orderService.setState(order_idx, StateOrder.ORDER_FAIL);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/complete/mobile")
    public ResponseEntity<?> completeMobileGet(@RequestParam(name = "imp_uid") String imp_uid,
                                               @RequestParam(name = "merchant_uid") String merchant_uid) {
        PaymentResultDto pdto = new PaymentResultDto(imp_uid, merchant_uid);
        return completePost(pdto);
    }

    @PostMapping("/complete/mobile")
    public ResponseEntity<?> completeMobilePost(@RequestParam(name = "imp_uid") String imp_uid,
                                      @RequestParam(name = "merchant_uid") String merchant_uid) {
        PaymentResultDto pdto = new PaymentResultDto(imp_uid, merchant_uid);
        return completePost(pdto);
    }

    @GetMapping("/complete")
    public ResponseEntity<?> completeGet(@RequestBody PaymentResultDto pdto) {
        return completePost(pdto);
    }

    @PostMapping("/complete")
    public ResponseEntity<?> completePost(@RequestBody PaymentResultDto pdto) {
        Order order = paymentService.complete(pdto);
        if(order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
