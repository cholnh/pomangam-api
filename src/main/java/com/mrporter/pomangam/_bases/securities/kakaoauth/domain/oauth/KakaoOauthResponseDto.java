package com.mrporter.pomangam._bases.securities.kakaoauth.domain.oauth;

import lombok.Data;

import java.io.Serializable;

@Data
public class KakaoOauthResponseDto implements Serializable {
    Integer id;
    String connected_at;
    Properties properties;
    KakaoAccount kakao_account;
}