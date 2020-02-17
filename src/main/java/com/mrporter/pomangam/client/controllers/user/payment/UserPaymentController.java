package com.mrporter.pomangam.client.controllers.user.payment;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/{phoneNumber}/payments")
@AllArgsConstructor
public class UserPaymentController {

    @GetMapping
    public ResponseEntity<?> findByPhoneNumber(
            @PathVariable(value = "phoneNumber", required = true) String phoneNumber,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> findByPhoneNumberAndIdx(
            @PathVariable(value = "phoneNumber", required = true) String phoneNumber,
            @PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> count(
            @PathVariable(value = "phoneNumber", required = true) String phoneNumber
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
