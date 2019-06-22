package com.mrporter.pomangam.common.fcm.controller;

import com.mrporter.pomangam.common.fcm.domain.FcmToken;
import com.mrporter.pomangam.common.fcm.service.FcmServiceImpl;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/fcms")
@AllArgsConstructor
public class FcmController {

    FcmServiceImpl fcmService;

    @PostMapping(value = "/send/ToAll", produces = {"application/json"})
    public ResponseEntity<?> sendToAll(@RequestBody Map<String, Object> paramInfo) throws JSONException {

        String firebaseResponse = fcmService.sendToAll(paramInfo);
        if(firebaseResponse == null) {
            return new ResponseEntity<>("Push Notification Error", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/send/ToDeliverySiteIdx", produces = {"application/json"})
    public ResponseEntity<?> sendToDeliverySiteIdx(@RequestBody Map<String, Object> paramInfo,
                                                   @RequestParam(value = "deliverySiteIdx") Integer deliverySiteIdx) throws JSONException {

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
