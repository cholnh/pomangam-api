package com.mrporter.pomangam._bases.securities.kakaoauth.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class KakaoAuthDto implements Serializable {

    private Integer idx;

    private String phone_number;

    private String auth_code;

    private String ip;

    private Timestamp register_date;

    public KakaoAuthDto(Integer idx, String phone_number, String auth_code, String ip, Timestamp register_date) {
        this.idx = idx;
        this.phone_number = phone_number;
        this.auth_code = auth_code;
        this.ip = ip;
        this.register_date = register_date;
    }
}