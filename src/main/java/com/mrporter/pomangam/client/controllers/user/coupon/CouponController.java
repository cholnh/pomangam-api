package com.mrporter.pomangam.client.controllers.user.coupon;

import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.coupon.CouponDto;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import com.mrporter.pomangam.client.services.user.coupon.CouponServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/users/{phoneNumber}/coupons")
@RestController
@AllArgsConstructor
public class CouponController {

    UserJpaRepository userRepo;
    CouponServiceImpl couponService;

    @GetMapping
    public ResponseEntity<?> findAllByPhoneNumber(
            @PathVariable(value = "phoneNumber", required = true) String phoneNumber,
            @RequestParam(value = "code", required = false) String code,
            Principal principal
    ) {
        if(code == null) {
            Long uIdx = userRepo.findIdxByPhoneNumberAndIsActiveIsTrue(principal.getName());
            if(uIdx == null) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(couponService.findAllByIdxUser(uIdx), HttpStatus.OK);
        } else {
            return new ResponseEntity(couponService.findOneByCode(code), HttpStatus.OK);
        }
    }

    @GetMapping("/{cIdx}")
    public ResponseEntity<?> findOneByPhoneNumberAndIdx(
            @PathVariable(value = "phoneNumber", required = true) String phoneNumber,
            @PathVariable(value = "cIdx", required = true) Long cIdx,
            Principal principal
    ) {
        Long uIdx = userRepo.findIdxByPhoneNumberAndIsActiveIsTrue(principal.getName());
        CouponDto dto = couponService.findOneByIdx(cIdx);
        if(uIdx != null && dto.getIdxUser().compareTo(uIdx) == 0) {
            return new ResponseEntity(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PostMapping("/{code}")
    public ResponseEntity<?> saveOneByCode(
            @PathVariable(value = "code", required = true) String code,
            Principal principal
    ) {
        User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(principal.getName());
        return new ResponseEntity(couponService.saveOneByCode(code, user), HttpStatus.OK);
    }
}
