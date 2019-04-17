package com.mrporter.pomangam.common.security.kakaoAuth.repository;

public interface KakaoAuthRepository {
    boolean checkAbusing(String ip);
    void saveAuthCode(String phone_number, String auth_code);
    boolean checkAuthCode(String phone_number, String auth_code);
    boolean checkAuthCodeNotDelete(String phone_number, String auth_code);
}
