package com.mrporter.pomangam.common.security.kakaoAuth.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrporter.pomangam.common.security.kakaoAuth.domain.UpdateInputDto;
import com.mrporter.pomangam.common.security.kakaoAuth.service.KakaoAuthServiceImpl;
import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.service.UserServiceImpl;
import com.mrporter.pomangam.common.util.formatter.PhoneNumberFormatter;
import com.mrporter.pomangam.common.util.security.Ip;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/kakaos")
@RestController
@AllArgsConstructor
public class KakaoAuthController {

    KakaoAuthServiceImpl kakaoAuthService;
    UserServiceImpl userService;

    @GetMapping("/generateAuthCodeForId")
    public ResponseEntity<?> generateAuthCode(@RequestParam("phn") String phone_number) {
        if(kakaoAuthService.checkAbusing(Ip.getInfo())) {
            return new ResponseEntity(HttpStatus.TOO_MANY_REQUESTS);
        }
        phone_number = PhoneNumberFormatter.format(phone_number);

        String auth_code = kakaoAuthService.getAuthCode();
        ResponseEntity<?> sendResult = kakaoAuthService.sendAuthCode(phone_number, auth_code);

        List<ApiResultBean> bean = new Gson().fromJson(sendResult.getBody()+"", new TypeToken<List<ApiResultBean>>() {}.getType());
        if(bean.get(0).getCode().equals("success")) {
            // success
            //AuthCode result = new AuthCode(auth_code, CustomTime.curDateTime());
            kakaoAuthService.saveAuthCode(phone_number, auth_code);
            return new ResponseEntity(bean.get(0), HttpStatus.OK);
        } else {
            // fail
            return new ResponseEntity(bean.get(0), sendResult.getHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/checkAuthCodeForId")
    public ResponseEntity<?> checkAuthCodeForId(@RequestParam("phn") String phone_number,
                                                @RequestParam("code") String auth_code) {
        phone_number = PhoneNumberFormatter.format(phone_number);
        if(kakaoAuthService.checkAuthCode(phone_number, auth_code)) {
            User user = userService.findByPhoneNumber(phone_number);
            if(user == null) {
                return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
            }
            user.setPw(null);
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/generateAuthCodeForJoin")
    public ResponseEntity<?> generateAuthCodeForJoin(@RequestParam("phn") String phone_number) {
        phone_number = PhoneNumberFormatter.format(phone_number);
        if(userService.isUserExistByPhoneNumber(phone_number)) {
            return new ResponseEntity("User already registered ", HttpStatus.BAD_REQUEST);
        }
        return generateAuthCode(phone_number);
    }

    @GetMapping("/checkAuthCodeForJoin")
    public ResponseEntity<?> checkAuthCode(@RequestParam("phn") String phone_number,
                                           @RequestParam("code") String auth_code) {
        phone_number = PhoneNumberFormatter.format(phone_number);
        boolean isValidAuthCode = kakaoAuthService.checkAuthCode(phone_number, auth_code);
        if(isValidAuthCode) {
            return new ResponseEntity(isValidAuthCode, HttpStatus.OK);
        } else {
            return new ResponseEntity(isValidAuthCode, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/generateAuthCodeForPw")
    public ResponseEntity<?> generateAuthCodeForPw(@RequestParam("id") String id,
                                                   @RequestParam("phn") String phone_number) {
        phone_number = PhoneNumberFormatter.format(phone_number);
        if(!userService.isUserExistByIdAndPhoneNumber(id, phone_number)) {
            return new ResponseEntity("User not found", HttpStatus.BAD_REQUEST);
        }
        return generateAuthCode(phone_number);
    }

    @GetMapping("/checkAuthCodeForPw")
    public ResponseEntity<?> checkAuthCodeForPw(@RequestParam("phn") String phone_number,
                                                @RequestParam("code") String auth_code) {
        phone_number = PhoneNumberFormatter.format(phone_number);
        return new ResponseEntity(kakaoAuthService.checkAuthCodeNotDelete(phone_number, auth_code), HttpStatus.OK);
    }

    @PostMapping("/updatePw")
    public ResponseEntity<?> updatePw(@RequestParam("phn") String phone_number,
                                      @RequestParam("code") String auth_code,
                                      @RequestBody UpdateInputDto dto) {
        phone_number = PhoneNumberFormatter.format(phone_number);
        if(kakaoAuthService.checkAuthCode(phone_number, auth_code)) {
            if(userService.isUserExistByIdAndPhoneNumber(dto.getId(), phone_number)) {
                User user = userService.updateUserPw(dto.getId(), dto.getPw());
                user.setPw(null);
                return new ResponseEntity(user, HttpStatus.OK);
            } else {
                return new ResponseEntity("USER NOT EXIST", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }

    @Data
    @AllArgsConstructor
    public class ApiResultBean {
        String code;
        Data data;
        String message;

        @lombok.Data
        @AllArgsConstructor
        class Data {
            String msgid;
        }
    }

    @Data
    @AllArgsConstructor
    class AuthCode {
        String auth_code;
        String register_date;
    }
}
