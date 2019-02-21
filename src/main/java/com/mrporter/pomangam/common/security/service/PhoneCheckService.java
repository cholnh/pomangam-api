package com.mrporter.pomangam.common.security.service;

import org.springframework.http.ResponseEntity;

public interface PhoneCheckService {
    String getAuthCode();
    ResponseEntity<?> sendAuthCode(String phone_number, String auth_code);
}
