package com.mrporter.pomangam.common.security.kakaoAuth.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "kakao_authcode_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class KakaoAuth implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    private String phone_number;

    private String auth_code;

    private Timestamp register_date;

    private String ip;

    @Builder
    public KakaoAuth(String phone_number, String auth_code, Timestamp register_date, String ip) {
        this.phone_number = phone_number;
        this.auth_code = auth_code;
        this.register_date = register_date;
        this.ip = ip;
    }
}
