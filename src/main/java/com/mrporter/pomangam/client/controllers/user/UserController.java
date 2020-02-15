package com.mrporter.pomangam.client.controllers.user;

import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.UserDto;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    UserServiceImpl userService;

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_USER') or hasRole('ROLE_ADMIN'))")
    @PostAuthorize("( returnObject != null && returnObject.phoneNumber == principal.username )")
    @GetMapping("/{phn}")
    public UserDto searchInfo(
            @PathVariable(value = "phn") String phoneNumber,
            Principal principal) {

        return UserDto.fromEntity(removePassword(userService.findByPhoneNumber(phoneNumber)));
    }

    @GetMapping("/search/exist/phone")
    public ResponseEntity<?> isExistByPhone(@RequestParam(value = "phn", required = true) String phoneNumber) {
        return new ResponseEntity(userService.isExistByPhone(phoneNumber), HttpStatus.OK);
    }

    @GetMapping("/search/exist/nickname")
    public ResponseEntity<?> isExistByNickname(@RequestParam(value = "nickname", required = true) String nickname) {
        return new ResponseEntity(userService.isExistByNickname(nickname), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody UserDto dto) {
        return new ResponseEntity<>(UserDto.fromEntity(removePassword(userService.saveUser(dto.toEntity()))), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or ( #phoneNumber == principal.username ))")
    @DeleteMapping("/{phn}")
    public ResponseEntity delete(@PathVariable(value = "phn") String phoneNumber) {
        return new ResponseEntity<>(userService.deleteUser(phoneNumber), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or ( #phoneNumber == principal.username ))")
    @PatchMapping("/{phn}")
    public ResponseEntity patch(@PathVariable(value = "phn") String phoneNumber,
                                @RequestBody UserDto dto) {
        try {
            return new ResponseEntity<>(UserDto.fromEntity(removePassword(userService.patchUser(phoneNumber, dto.toEntity()))), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private User removePassword(User user) {
        if(user != null) {
            user.setPassword(null);
        }
        return user;
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_STAFF') or hasRole('ROLE_ADMIN'))")
    @GetMapping
    public ResponseEntity<?> findRecentlyRegistered(@RequestParam(value = "limit", required = true) Integer limit) {
        return new ResponseEntity(userService.findRecentlyRegistered(limit), HttpStatus.OK);
    }
}
