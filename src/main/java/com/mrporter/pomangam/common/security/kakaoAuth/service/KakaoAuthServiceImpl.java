package com.mrporter.pomangam.common.security.kakaoAuth.service;

import com.mrporter.pomangam.common.security.kakaoAuth.repository.KakaoAuthRepositoryImpl;
import com.mrporter.pomangam.common.util.apiClient.BizmApi;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@AllArgsConstructor
public class KakaoAuthServiceImpl implements KakaoAuthService {

    KakaoAuthRepositoryImpl kakaoAuthRepository;

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
        for (int i = 0; i < 6; i++) {
            builder.append(secureRandom.nextInt(10));
        }
        return builder.toString();
    }

    public boolean checkAbusing(String ip) {
        return kakaoAuthRepository.checkAbusing(ip);
    }

    @Override
    public ResponseEntity<?> sendAuthCode(String phone_number, String auth_code) {
        return BizmApi.send(phone_number, (msg1 + auth_code + msg2), tmplId);
    }

    @Override
    public void saveAuthCode(String phone_number, String auth_code) {
        kakaoAuthRepository.saveAuthCode(phone_number, auth_code);
    }

    @Override
    public boolean checkAuthCode(String phone_number, String auth_code) {
        return kakaoAuthRepository.checkAuthCode(phone_number, auth_code);
    }

    @Override
    public boolean checkAuthCodeNotDelete(String phone_number, String auth_code) {
        return kakaoAuthRepository.checkAuthCodeNotDelete(phone_number, auth_code);
    }
}
