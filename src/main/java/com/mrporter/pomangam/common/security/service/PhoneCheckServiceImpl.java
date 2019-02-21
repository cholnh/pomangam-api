package com.mrporter.pomangam.common.security.service;

import com.mrporter.pomangam.common.util.apiClient.BizmApi;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@AllArgsConstructor
public class PhoneCheckServiceImpl implements PhoneCheckService {

    private final static String msg1 = "[포만감] 인증번호 : ";
    private final static String msg2 = "\n정확히 입력해주세요.";
    private final static String tmplId = "pmg_auth_1";

    @Override
    public String getAuthCode() {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<4; i++) {
            builder.append(secureRandom.nextInt(10));
        }
        return builder.toString();
    }

    @Override
    public ResponseEntity<?> sendAuthCode(String phone_number, String auth_code) {
        return BizmApi.send(phone_number, (msg1 + auth_code + msg2), tmplId);
    }
}
