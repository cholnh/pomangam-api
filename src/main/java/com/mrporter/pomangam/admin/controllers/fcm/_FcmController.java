package com.mrporter.pomangam.admin.controllers.fcm;

import com.mrporter.pomangam.admin.services.fcm._FcmServiceImpl;
import com.mrporter.pomangam.client.domains.fcm.FcmToken;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
@RestController
@RequestMapping("/admin/fcms")
@AllArgsConstructor
public class _FcmController {

    _FcmServiceImpl fcmService;

    @PostMapping(value = "/send/all", produces = {"application/json"})
    public ResponseEntity<?> sendToAll(
        @RequestBody Map<String, Object> paramInfo
    ) throws JSONException {

        String firebaseResponse = fcmService.sendToAll(paramInfo);
        if(firebaseResponse == null) {
            return new ResponseEntity<>("Push Notification Error", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/send/dsite", produces = {"application/json"})
    public ResponseEntity<?> sendToDeliverySiteIdx(
        @RequestBody Map<String, Object> paramInfo,
        @RequestParam(value = "didx") Long deliverySiteIdx
    ) throws JSONException {

        String firebaseResponse = fcmService.sendToDeliverySiteIdx(paramInfo, deliverySiteIdx);
        if(firebaseResponse == null) {
            return new ResponseEntity<>("Push Notification Error", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody FcmToken token) {
        return ResponseEntity.ok(fcmService.post(token));
    }

    @PatchMapping
    public ResponseEntity<?> patch(@RequestBody FcmToken token) {
        return ResponseEntity.ok(fcmService.patch(token));
    }
}
