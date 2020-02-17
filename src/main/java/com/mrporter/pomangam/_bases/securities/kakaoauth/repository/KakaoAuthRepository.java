package com.mrporter.pomangam._bases.securities.kakaoauth.repository;

public interface KakaoAuthRepository {
    boolean checkAbusing(String ip);
    void saveAuthCode(String phone_number, String auth_code);
    boolean checkAuthCode(String phone_number, String auth_code);
    boolean checkAuthCodeNotDelete(String phone_number, String auth_code);
}
