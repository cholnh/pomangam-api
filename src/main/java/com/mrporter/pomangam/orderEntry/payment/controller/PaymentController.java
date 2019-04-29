package com.mrporter.pomangam.orderEntry.payment.controller;

import com.mrporter.pomangam.orderEntry.order.domain.Order;
import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;
import com.mrporter.pomangam.orderEntry.order.service.OrderService;
import com.mrporter.pomangam.orderEntry.payment.domain.PaymentInputDto;
import com.mrporter.pomangam.orderEntry.payment.domain.PaymentResultDto;
import com.mrporter.pomangam.orderEntry.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/payments")
@RestController
@AllArgsConstructor
public class PaymentController {

    PaymentService paymentService;
    OrderService orderService;

    @PostMapping("/prepare")
    public ResponseEntity<?> prepare(@RequestBody PaymentInputDto dto) {
        Order order = paymentService.prepare(dto);
        if(order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}/fail")
    public ResponseEntity<?> fail(@PathVariable("id") Integer order_idx) {
        orderService.setState(order_idx, StateOrder.ORDER_FAIL);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/complete/mobile")
    public ResponseEntity<?> completeMobileGetOne(@RequestParam("imp_uid") String imp_uid,
                                                  @RequestParam(value = "merchant_uid", required = false) String merchant_uid) {
        System.out.println(imp_uid + " " + merchant_uid);
        Order order = paymentService.complete(imp_uid);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

/*
    @GetMapping("/complete/mobile")
    public ResponseEntity<?> completeMobileGet(@RequestParam("imp_uid") String imp_uid,
                                               @RequestParam("merchant_uid") String merchant_uid) {
        System.out.println(imp_uid + " " + merchant_uid);
        PaymentResultDto pdto = new PaymentResultDto(imp_uid, merchant_uid);
        return completePost(pdto);
    }
*/
    @PostMapping("/complete/mobile")
    public ResponseEntity<?> completeMobilePost(@RequestParam("imp_uid") String imp_uid,
                                      @RequestParam("merchant_uid") String merchant_uid) {
        System.out.println(imp_uid + " " + merchant_uid);
        PaymentResultDto pdto = new PaymentResultDto(imp_uid, merchant_uid);
        return completePost(pdto);
    }

    @GetMapping("/complete")
    public ResponseEntity<?> completeGet(@RequestBody PaymentResultDto pdto) {
        System.out.println(pdto);
        return completePost(pdto);
    }

    @PostMapping("/complete")
    public ResponseEntity<?> completePost(@RequestBody PaymentResultDto pdto) {
        System.out.println(pdto);
        Order order = null;//paymentService.complete(pdto);
        if(order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
    }

}
