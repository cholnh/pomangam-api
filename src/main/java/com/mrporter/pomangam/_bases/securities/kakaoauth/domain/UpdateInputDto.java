package com.mrporter.pomangam._bases.securities.kakaoauth.domain;

import lombok.Data;

@Data
public class UpdateInputDto extends PhoneNumber {
    private String password;
}
