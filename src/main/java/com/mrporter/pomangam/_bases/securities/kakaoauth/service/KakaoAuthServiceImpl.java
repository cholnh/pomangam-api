package com.mrporter.pomangam._bases.securities.kakaoauth.service;

import com.mrporter.pomangam._bases.securities.kakaoauth.repository.KakaoAuthRepositoryImpl;
import com.mrporter.pomangam._bases.utils.apiclient.BizmApi;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@AllArgsConstructor
public class KakaoAuthServiceImpl implements KakaoAuthService {

    KakaoAuthRepositoryImpl kakaoAuthRepository;
    UserJpaRepository userRepo;

    private final static String msg1 = "[포만감] 인증번호 : ";
    private final static String msg2 = "\n정확히 입력해주세요.";
    private final static String tmplId = "pmg_auth_1";
    private final static int authCodeLength = 4;

    public static int getAuthCodeLength() {
        return authCodeLength;
    }

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
        for (int i = 0; i < authCodeLength; i++) {
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
        User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(phone_number);
        user.getPassword().setFailedCount(0);
        userRepo.save(user);
        return kakaoAuthRepository.checkAuthCode(phone_number, auth_code);
    }

    @Override
    public boolean checkAuthCodeNotDelete(String phone_number, String auth_code) {
        User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(phone_number);
        user.getPassword().setFailedCount(0);
        userRepo.save(user);
        return kakaoAuthRepository.checkAuthCodeNotDelete(phone_number, auth_code);
    }

    public void deleteAuthCode(String phone_number) {
        kakaoAuthRepository.deleteAuthCode(phone_number);
    }
}
