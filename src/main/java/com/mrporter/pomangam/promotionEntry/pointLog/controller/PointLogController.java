package com.mrporter.pomangam.promotionEntry.pointLog.controller;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.service.UserServiceImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.promotionEntry.pointLog.service.PointLogServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequestMapping("/pointLogs")
@RestController
@AllArgsConstructor
public class PointLogController {

    UserServiceImpl userService;
    PointLogServiceImpl pointLogService;

    @PreAuthorize("isAuthenticated() and !hasRole('ROLE_GUEST')")
    @GetMapping("/search/myInfo")
    public ResponseEntity<?> myInfo(Principal principal, PageRequest pageRequest) {
        User user = userService.findById(principal.getName());
        return new ResponseEntity(pointLogService.findByCustomerIdx(user.getIdx(), pageRequest), HttpStatus.OK);
    }
}
