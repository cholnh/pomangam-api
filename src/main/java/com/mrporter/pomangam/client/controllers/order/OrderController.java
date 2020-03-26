package com.mrporter.pomangam.client.controllers.order;

import com.mrporter.pomangam.client.domains.order.OrderRequestDto;
import com.mrporter.pomangam.client.domains.order.OrderResponseDto;
import com.mrporter.pomangam.client.domains.order.orderer.OrdererType;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.UserDto;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import com.mrporter.pomangam.client.services.order.OrderServiceImpl;
import com.mrporter.pomangam.client.services.order.exception.OrderException;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
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

    OrderServiceImpl orderService;
    UserJpaRepository userRepo;

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or ( #phoneNumber == principal.username ))")
    @GetMapping("/{phn}")
    public ResponseEntity<List<OrderResponseDto>> findByPhoneNumber(
            @PathVariable(value = "phn", required = true) String phoneNumber,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {

        return new ResponseEntity<>(orderService.findByPhoneNumber(phoneNumber, pageable), HttpStatus.OK);
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
            orderDto.setIdxFcmToken(user.getIdxFcmToken());
            orderDto.setOrdererType(OrdererType.USER);
        } else {
            if(orderDto.getIdxFcmToken() == null) {
                throw new OrderException("fcm Token index is null");
            }
            orderDto.setUser(null);
            orderDto.setOrdererType(OrdererType.GUEST);
        }
        return new ResponseEntity<>(orderService.save(orderDto), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{oIdx}/verify")
    public ResponseEntity<Boolean> verify(
            @PathVariable(value = "oIdx", required = true) Long oIdx
    ) {
        return new ResponseEntity<>(orderService.verify(oIdx), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{oIdx}/cancel")
    public ResponseEntity<?> cancel(
            @PathVariable(value = "oIdx", required = true) Long oIdx
    ) {
        orderService.cancel(oIdx);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
