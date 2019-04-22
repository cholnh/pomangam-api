package com.mrporter.pomangam.orderEntry.order.controller;

import com.mrporter.pomangam.orderEntry.order.domain.OrderInputDto;
import com.mrporter.pomangam.orderEntry.order.domain.StateOrder;
import com.mrporter.pomangam.orderEntry.order.service.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
