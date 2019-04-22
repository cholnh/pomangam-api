package com.mrporter.pomangam.promotionEntry.coupon.controller;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.util.security.SessionConverter;
import com.mrporter.pomangam.promotionEntry.coupon.domain.CouponDto;
import com.mrporter.pomangam.promotionEntry.coupon.service.CouponServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/coupons")
@RestController
@AllArgsConstructor
public class CouponController {

    CouponServiceImpl couponService;

    @GetMapping("/search/countCoupon")
    public ResponseEntity<?> countCoupon(@RequestParam("customerIdx") Integer customerIdx) {
        try {
            return new ResponseEntity(couponService.countCoupon(customerIdx), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search/myValidCoupon")
    public ResponseEntity<?> findValidByCustomerIdx(HttpSession session) {
        try {
            User user = SessionConverter.getCustomer(session);
            Authentication authentication = SessionConverter.getAuthentication(session);
            if(authentication == null || user == null) {
                return new ResponseEntity("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
            }

            List<CouponDto> dtoList = couponService.findValidByCustomerIdx(user.getIdx());
            return new ResponseEntity(dtoList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/getValidCoupon")
    public ResponseEntity<?> getValidCouponByCode(@RequestParam("couponCode") String couponCode) {
        try {
            CouponDto dto = couponService.getValidCouponByCode(couponCode);
            if(dto == null) {
                return new ResponseEntity("INVALID COUPON CODE", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity(dto, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/isValid")
    public ResponseEntity<?> isValid(@RequestParam("couponCode") String couponCode) {
        try {
            return new ResponseEntity(couponService.isValid(couponCode), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Payment 과정에서 CouponService 의 useCoupon을 사용하므로..
    @GetMapping("/useCoupon")
    public ResponseEntity<?> useCoupon(@RequestParam("couponIdx") Integer couponIdx,
                                       @RequestParam("orderIdx") Integer orderIdx,
                                       HttpSession session) {
        try {
            // 비회원 쿠폰 사용 불가 -> 회원가입 유도
            User user = SessionConverter.getCustomer(session);
            Authentication authentication = SessionConverter.getAuthentication(session);
            if(authentication == null || user == null) {
                return new ResponseEntity("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
            }

            couponService.useCoupon(couponIdx, user.getIdx(), orderIdx);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     */

}
