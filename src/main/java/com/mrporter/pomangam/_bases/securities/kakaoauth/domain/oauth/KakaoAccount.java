package com.mrporter.pomangam._bases.securities.kakaoauth.domain.oauth;

import lombok.Data;

import java.io.Serializable;

@Data
public class KakaoAccount implements Serializable {
    Boolean profile_needs_agreement;
    Profile profile;
    Boolean has_phone_number;
    Boolean phone_number_needs_agreement;
    String phone_number;
    Boolean has_birthyear;
    Boolean birthyear_needs_agreement;
    Boolean has_birthday;
    Boolean birthday_needs_agreement;
    Boolean has_gender;
    Boolean gender_needs_agreement;
    String gender;
}
