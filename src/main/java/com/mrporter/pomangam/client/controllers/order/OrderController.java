package com.mrporter.pomangam.client.controllers.order;

import com.mrporter.pomangam.client.domains.order.OrderResponseDto;
import com.mrporter.pomangam.client.services.order.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    OrderServiceImpl orderService;

    @GetMapping("/{idx}")
    public ResponseEntity<?> findByIdx(
            @PathVariable(value = "idx") Long idx
    ) {
        return new ResponseEntity(orderService.findByIdx(idx), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(
            @RequestBody OrderResponseDto orderDto
    ) {
        System.out.println(orderDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
