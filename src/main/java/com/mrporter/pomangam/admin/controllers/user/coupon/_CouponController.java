package com.mrporter.pomangam.admin.controllers.user.coupon;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
@RequestMapping("/admin/coupons")
@RestController
@AllArgsConstructor
public class _CouponController {

    @GetMapping
    public ResponseEntity<?> get(
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getByIdx(
            @PathVariable(value = "idx", required = true) Integer idx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> searchCount() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
