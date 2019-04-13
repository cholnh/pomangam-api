package com.mrporter.pomangam.common.security.user.controller;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.domain.UserDto;
import com.mrporter.pomangam.common.util.security.SessionConverter;
import com.mrporter.pomangam.common.security.user.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    UserServiceImpl userService;

    @PostAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<?> get(
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 5) Pageable pageable) {

        //localhost:8080/user?page=0&size=10&sort=idx,desc
        Page<User> pages =  userService.findAllUsers(pageable);
        return new ResponseEntity<>(pages, HttpStatus.OK);
    }

    //@PostAuthorize("isAuthenticated() and (( returnObject.id == principal.id ) or hasRole('ROLE_ADMIN'))")
    @PostAuthorize("isAuthenticated()")
    @GetMapping("/myInfo")
    public ResponseEntity<?> get(HttpSession session) {
        User user = SessionConverter.getCustomer(session);
        Authentication authentication = SessionConverter.getAuthentication(session);
        if(authentication != null && user != null) {
            return ResponseEntity.ok(removePassword(user));
        } else {
            return new ResponseEntity("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
        }
    }

    private User removePassword(User user) {
        user.setPw(null);
        return user;
    }

    @GetMapping("/isExist")
    public ResponseEntity<?> get(@RequestParam("id") String id) {
        if(id == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.isUserExist(id));
    }

    // 비밀번호 암호화 필요
    @PostMapping
    public ResponseEntity<?> post(@RequestBody UserDto dto) {
        return new ResponseEntity<>(
                removePassword(userService.saveUser(dto.toEntity())),
                HttpStatus.OK);
    }

    //@PreAuthorize("isAuthenticated() and (( id == principal.id ) or hasRole('ROLE_ADMIN'))")
    //@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id,
                                 HttpSession session) {

        User user = SessionConverter.getCustomer(session);
        Authentication authentication = SessionConverter.getAuthentication(session);

        if(user != null) {
            if(id.equals(user.getId()) || (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))) {
                return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
            }
        }
        return new ResponseEntity("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
    }

    //@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    //@PreAuthorize("isAuthenticated() and (( id == principal.id ) or hasRole('ROLE_ADMIN'))")
    @PutMapping("/{id}")
    public ResponseEntity put(
            @PathVariable String id,
            @RequestBody UserDto dto,
            HttpSession session) {

        User user = SessionConverter.getCustomer(session);
        Authentication authentication = SessionConverter.getAuthentication(session);
        if(user != null) {
            if(id.equals(user.getId()) || (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))) {
                return new ResponseEntity<>(
                        removePassword(userService.updateUser(id, dto.toEntity())),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
    }

    //@PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    //@PreAuthorize("isAuthenticated() and (( id == principal.id ) or hasRole('ROLE_ADMIN'))")
    @PatchMapping("/{id}")
    public ResponseEntity patch(
            @PathVariable String id,
            @RequestBody UserDto dto,
            HttpSession session) {

        User user = SessionConverter.getCustomer(session);
        Authentication authentication = SessionConverter.getAuthentication(session);
        if(user != null) {
            if(id.equals(user.getId()) || (authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))) {
                return new ResponseEntity<>(
                        removePassword(userService.patchUser(id, dto.toEntity())),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity("UNAUTHORIZED",HttpStatus.UNAUTHORIZED);
    }
}
