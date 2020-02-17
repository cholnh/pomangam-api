package com.mrporter.pomangam._bases.securities.kakaoauth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateInputDto extends PhoneNumber {
    private String password;
}
