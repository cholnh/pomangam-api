package com.mrporter.pomangam.promotionEntry.couponLog.controller;

import com.mrporter.pomangam.promotionEntry.couponLog.service.CouponLogServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/couponLogs")
@RestController
@AllArgsConstructor
public class CouponLogController {

    CouponLogServiceImpl couponLogService;
}
