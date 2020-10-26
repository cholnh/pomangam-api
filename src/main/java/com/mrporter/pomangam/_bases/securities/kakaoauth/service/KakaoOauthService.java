package com.mrporter.pomangam._bases.securities.kakaoauth.service;

public interface KakaoOauthService {
    boolean verifyOauthLogin(String phoneNumber, String token);
}
