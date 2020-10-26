package com.mrporter.pomangam._bases.securities.kakaoauth.service;

import com.google.gson.Gson;
import com.mrporter.pomangam._bases.securities.kakaoauth.domain.oauth.KakaoOauthResponseDto;
import com.mrporter.pomangam._bases.utils.bizm.ApiClientUtils;
import com.mrporter.pomangam._bases.utils.formatter.PhoneNumberFormatter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class KakaoOauthServiceImpl implements KakaoOauthService {

    private static final String domain = "kapi.kakao.com";
    private static final boolean isSsl = true;

    public boolean verifyOauthLogin(String phoneNumber, String token) {
        boolean isValid = false;

        try{
            ApiClientUtils apiClientUtils = new ApiClientUtils(domain, isSsl);
            Map<String, String> header = new HashMap<>();
            header.put("Authorization", "Bearer " + token);
            ResponseEntity response = apiClientUtils.sendByPostNotBody(header, "/v2/user/me");
            if(response.getStatusCode() == HttpStatus.OK) {
                KakaoOauthResponseDto dto = new Gson().fromJson(response.getBody()+"", KakaoOauthResponseDto.class);

                if(dto.getKakao_account().getHas_phone_number()) {
                    String dtoPn = "0" + (dto.getKakao_account().getPhone_number().split(" ")[1]);
                    dtoPn = PhoneNumberFormatter.format(dtoPn);
                    phoneNumber = PhoneNumberFormatter.format(phoneNumber);
                    isValid = dtoPn.equals(phoneNumber);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
