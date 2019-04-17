package com.mrporter.pomangam.common.security.kakaoAuth.domain;

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

    private Timestamp register_date;

    private String ip;

    public KakaoAuthDto(Integer idx, String phone_number, String auth_code, Timestamp register_date, String ip) {
        this.idx = idx;
        this.phone_number = phone_number;
        this.auth_code = auth_code;
        this.register_date = register_date;
        this.ip = ip;
    }
}