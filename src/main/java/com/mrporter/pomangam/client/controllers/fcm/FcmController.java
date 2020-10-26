package com.mrporter.pomangam.client.controllers.fcm;

import com.mrporter.pomangam.client.domains.fcm.FcmRequestDto;
import com.mrporter.pomangam.client.domains.fcm.client.FcmClientTokenDto;
import com.mrporter.pomangam.client.domains.fcm.owner.FcmOwnerTokenDto;
import com.mrporter.pomangam.client.domains.fcm.staff.FcmStaffTokenDto;
import com.mrporter.pomangam.client.services.fcm.FcmServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fcms")
@AllArgsConstructor
public class FcmController {

    FcmServiceImpl fcmService;

    @PostMapping("/send")
    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_STAFF') or hasRole('ROLE_ADMIN'))")
    public ResponseEntity<?> send(@RequestBody FcmRequestDto request) {
        return ResponseEntity.ok(fcmService.send(request));
    }

    @PostMapping("/client")
    public ResponseEntity<?> postClient(@RequestBody FcmClientTokenDto token) {
        return ResponseEntity.ok(fcmService.postClient(token));
    }

    @PostMapping("/owner")
    public ResponseEntity<?> postOwner(@RequestBody FcmOwnerTokenDto token) {
        return ResponseEntity.ok(fcmService.postOwner(token));
    }

    @PostMapping("/staff")
    public ResponseEntity<?> postStaff(@RequestBody FcmStaffTokenDto token) {
        return ResponseEntity.ok(fcmService.postStaff(token));
    }

    @DeleteMapping("/client/{fIdx}")
    public ResponseEntity<?> deleteClient(
            @PathVariable(value = "fIdx") Long fIdx
    ) {
        fcmService.deleteClient(fIdx);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/owner/{fIdx}")
    public ResponseEntity<?> deleteOwner(
            @PathVariable(value = "fIdx") Long fIdx
    ) {
        fcmService.deleteOwner(fIdx);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/staff/{fIdx}")
    public ResponseEntity<?> deleteStaff(
            @PathVariable(value = "fIdx") Long fIdx
    ) {
        fcmService.deleteStaff(fIdx);
        return ResponseEntity.ok(true);
    }
}
