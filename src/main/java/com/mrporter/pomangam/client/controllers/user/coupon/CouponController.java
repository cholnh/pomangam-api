package com.mrporter.pomangam.client.controllers.user.coupon;

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

@RequestMapping("/users/{id}/coupons")
@RestController
@AllArgsConstructor
public class CouponController {

    @GetMapping
    public ResponseEntity<?> getByUser(
            @PathVariable(value = "id", required = true) String id,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getByUserAndIdx(
            @PathVariable(value = "id", required = true) String id,
            @PathVariable(value = "idx", required = true) Integer idx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> searchCountByUser(
            @PathVariable(value = "id", required = true) String id
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
