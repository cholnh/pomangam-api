package com.mrporter.pomangam._bases.securities.kakaoauth.domain;

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

    private String ip;

    private Timestamp register_date;

    @Builder
    public KakaoAuth(String phone_number, String auth_code, String ip, Timestamp register_date) {
        this.phone_number = phone_number;
        this.auth_code = auth_code;
        this.ip = ip;
        this.register_date = register_date;
    }
}
