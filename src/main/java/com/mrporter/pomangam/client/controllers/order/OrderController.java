package com.mrporter.pomangam.client.controllers.order;

import com.mrporter.pomangam._bases.utils.bootpay.model.response.callback.CallbackResponse;
import com.mrporter.pomangam.client.domains.order.OrderRequestDto;
import com.mrporter.pomangam.client.domains.order.OrderResponseDto;
import com.mrporter.pomangam.client.domains.order.bootpay.BootpayVbankDto;
import com.mrporter.pomangam.client.domains.order.orderer.OrdererType;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import com.mrporter.pomangam.client.services.order.OrderServiceImpl;
import com.mrporter.pomangam.client.services.order.exception.OrderException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    final String BOOTPAY_PRIVATE_KEY = "/LokN9TIOXtFpmIo9iUEl76GllpuKljfbR0ndCviINU=";

    OrderServiceImpl orderService;
    UserJpaRepository userRepo;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> findAllByIdxFcmToken(
            @RequestParam(value = "fIdx", required = false) Long fIdx,
            @RequestParam(value = "pn", required = false) String phoneNumber,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable,
            Authentication auth
    ) {
        if(fIdx != null) {
            return new ResponseEntity<>(orderService.findAllByIdxFcmToken(fIdx, pageable), HttpStatus.OK);
        } else if(phoneNumber != null) {
            if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(auth.getName());
                if(user.getPhoneNumber().equals(phoneNumber)) {
                    return new ResponseEntity<>(orderService.findAllByPhoneNumber(phoneNumber, pageable), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/today")
    public ResponseEntity<List<OrderResponseDto>> findTodayByIdxFcmToken(
            @RequestParam(value = "fIdx", required = false) Long fIdx,
            @RequestParam(value = "pn", required = false) String phoneNumber,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable,
            Authentication auth
    ) {
        if(fIdx != null) {
            return new ResponseEntity<>(orderService.findTodayByIdxFcmToken(fIdx, pageable), HttpStatus.OK);
        } else if(phoneNumber != null) {
            if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(auth.getName());
                if(user.getPhoneNumber().equals(phoneNumber)) {
                    return new ResponseEntity<>(orderService.findTodayByPhoneNumber(phoneNumber, pageable), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<OrderResponseDto> post(
            @RequestBody OrderRequestDto orderDto,
            Authentication auth
    ) {
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(auth.getName());
            orderDto.setUser(user);
            orderDto.setOrdererType(OrdererType.USER);
        } else {
            if(orderDto.getIdxFcmToken() == null) {
                //throw new OrderException("fcm Token index is null");
            }
            orderDto.setUser(null);
            orderDto.setOrdererType(OrdererType.GUEST);
        }
        return new ResponseEntity<>(orderService.save(orderDto), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{oIdx}/verify")
    public ResponseEntity<Boolean> verify(
            @PathVariable(value = "oIdx", required = true) Long oIdx,
            @RequestParam(value = "receipt", required = true) String receipt_id
    ) {
        return new ResponseEntity<>(orderService.verify(oIdx, receipt_id), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{oIdx}/vbank")
    public ResponseEntity<?> getVbank(
            @PathVariable(value = "oIdx", required = true) Long oIdx
    ) {
        return new ResponseEntity<>(orderService.getVbank(oIdx), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{oIdx}/vbank")
    public ResponseEntity<?> postVbank(
            @PathVariable(value = "oIdx", required = true) Long oIdx,
            @RequestBody BootpayVbankDto dto
    ) {
        return new ResponseEntity<>(orderService.postVbank(dto), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{oIdx}/receipt")
    public ResponseEntity<?> postReceipt(
            @PathVariable(value = "oIdx", required = true) Long oIdx,
            @RequestParam(value = "receiptId", required = true) String receiptId
    ) {
        orderService.postReceipt(oIdx, receiptId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{oIdx}/paymentfail")
    public ResponseEntity<?> paymentFail(
            @PathVariable(value = "oIdx", required = true) Long oIdx
    ) {
        orderService.paymentFail(oIdx);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{oIdx}/cancel")
    public ResponseEntity<?> cancel(
            @PathVariable(value = "oIdx", required = true) Long oIdx
    ) {
        orderService.cancel(oIdx);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/callback")
    public ResponseEntity<?> callback(
            @RequestBody CallbackResponse response
    ) {
        // Ip.isBootpayIp()
        try {
            if(response.getPrivate_key().equals(BOOTPAY_PRIVATE_KEY)) {
                orderService.callback(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/today/count")
    public ResponseEntity<?> count(
            @RequestParam(value = "fIdx", required = false) Long fIdx,
            @RequestParam(value = "pn", required = false) String phoneNumber,
            Authentication auth
    ) {
        if(fIdx != null) {
            return new ResponseEntity<>(orderService.countByIdxFcmToken(fIdx), HttpStatus.OK);
        } else if(phoneNumber != null) {
            if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(auth.getName());
                if(user.getPhoneNumber().equals(phoneNumber)) {
                    return new ResponseEntity<>(orderService.countByPhoneNumber(phoneNumber), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{oIdx}")
    public ResponseEntity<OrderResponseDto> patchDetailSite(
            @PathVariable(value = "oIdx", required = true) Long oIdx,
            @RequestParam(value = "ddIdx", required = true) Long ddIdx
    ) {
        return new ResponseEntity<>(orderService.patchDetailSite(oIdx, ddIdx), HttpStatus.OK);
    }
}
