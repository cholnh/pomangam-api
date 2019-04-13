package com.mrporter.pomangam.common.security.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mrporter.pomangam.common.security.service.PhoneCheckServiceImpl;
import com.mrporter.pomangam.common.util.time.CustomTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/check/phoneNumber")
@RestController
@AllArgsConstructor
public class PhoneCheckController {

    PhoneCheckServiceImpl phoneCheckService;

    @GetMapping
    public ResponseEntity<?> checkPhoneNumber(@RequestParam("phn") String phone_number) {
        String auth_code = phoneCheckService.getAuthCode();
        ResponseEntity<?> sendResult = phoneCheckService.sendAuthCode(phone_number, auth_code);

        List<ApiResultBean> bean = new Gson().fromJson(sendResult.getBody()+"", new TypeToken<List<ApiResultBean>>() {}.getType());
        if(bean.get(0).getCode().equals("success")) {
            // success
            AuthCode result = new AuthCode(auth_code, CustomTime.curDateTime());
            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            // fail
            return new ResponseEntity(bean.get(0), sendResult.getHeaders(), HttpStatus.BAD_REQUEST);
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
