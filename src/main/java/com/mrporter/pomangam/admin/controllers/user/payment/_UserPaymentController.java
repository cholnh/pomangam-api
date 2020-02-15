package com.mrporter.pomangam.admin.controllers.user.payment;

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
@RestController
@RequestMapping("/admin/users/{phoneNumber}/payments")
@AllArgsConstructor
public class _UserPaymentController {

    @GetMapping
    public ResponseEntity<?> getByPhoneNumber(
            @PathVariable(value = "phoneNumber", required = true) String phoneNumber,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getByPhoneNumberAndIdx(
            @PathVariable(value = "phoneNumber", required = true) String phoneNumber,
            @PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> searchCount(
            @PathVariable(value = "phoneNumber", required = true) String phoneNumber
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
