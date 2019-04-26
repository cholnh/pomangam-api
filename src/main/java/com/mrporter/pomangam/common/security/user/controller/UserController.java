package com.mrporter.pomangam.common.security.user.controller;

import com.mrporter.pomangam.common.security.guest.service.GuestServiceImpl;
import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.domain.UserDto;
import com.mrporter.pomangam.common.security.user.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserServiceImpl userService;
    GuestServiceImpl guestService;

    @PostAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<?> get(
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 5) Pageable pageable) {

        Page<User> pages =  userService.findAllUsers(pageable);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and !hasRole('ROLE_GUEST')")
    @PostAuthorize("hasRole('ROLE_ADMIN') or ( returnObject != null && returnObject.id == principal.username )")
    @GetMapping("/myInfo")
    public User myInfo(Principal principal) {
        User user = userService.findById(principal.getName());
        return removePassword(user);
    }

    @GetMapping("/isExist")
    public ResponseEntity<?> isExist(@RequestParam("id") String id) {
        if(id == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.isUserExist(id));
    }

    @GetMapping("/isExistByPhoneNumber")
    public ResponseEntity<?> isUserExistByPhoneNumber(@RequestParam("phone_number") String phone_number) {
        if(phone_number == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userService.isUserExistByPhoneNumber(phone_number), HttpStatus.OK);
    }

    //
    @PostMapping
    public ResponseEntity<?> post(@RequestBody UserDto dto) {
        return new ResponseEntity<>(removePassword(userService.saveUser(dto.toEntity())), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or ( #id == principal.username ))")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or ( #id == principal.username ))")
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable String id,
                              @RequestBody UserDto dto) {
        return new ResponseEntity<>(removePassword(userService.updateUser(id, dto.toEntity())), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or ( #id == principal.username ))")
    @PatchMapping("/{id}")
    public ResponseEntity patch(@PathVariable String id,
                                @RequestBody UserDto dto) {
        return new ResponseEntity<>(removePassword(userService.patchUser(id, dto.toEntity())), HttpStatus.OK);
    }

    private User removePassword(User user) {
        if(user != null) {
            user.setPw(null);
        }
        return user;
    }
}
