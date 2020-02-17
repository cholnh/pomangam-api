package com.mrporter.pomangam.admin.controllers.user;

import com.mrporter.pomangam.admin.domains.user._UserDto;
import com.mrporter.pomangam.admin.services.user._UserServiceImpl;
import com.mrporter.pomangam.client.domains.user.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
@RestController
@RequestMapping("/admin/users")
@AllArgsConstructor
public class _UserController {

    _UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<?> get(
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
        List<User> users =  userService.findAll(pageable);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{phn}")
    public User searchInfo(@PathVariable(value = "phn") String phoneNumber) {
        User user = userService.findByPhoneNumber(phoneNumber);
        return removePassword(user);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody _UserDto dto) {
        return new ResponseEntity<>(removePassword(userService.saveUser(dto.toEntity())), HttpStatus.OK);
    }

    @DeleteMapping("/{phn}")
    public ResponseEntity delete(@PathVariable(value = "phn") String phoneNumber) {
        return new ResponseEntity<>(userService.deleteUser(phoneNumber), HttpStatus.OK);
    }

    @PatchMapping("/{phn}")
    public ResponseEntity patch(@PathVariable(value = "phn") String phoneNumber,
                                @RequestBody _UserDto dto) {
        return new ResponseEntity<>(removePassword(userService.patchUser(phoneNumber, dto.toEntity())), HttpStatus.OK);
    }

    private User removePassword(User user) {
        if(user != null) {
            user.setPassword(null);
        }
        return user;
    }
}
