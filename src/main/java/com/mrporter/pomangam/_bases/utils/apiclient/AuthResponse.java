package com.mrporter.pomangam._bases.utils.apiclient;

import lombok.Data;

@Data
public class AuthResponse {
    Integer code;
    String message;
    AuthAnnotation response;

    @Data
    class AuthAnnotation {
        String access_token;
        Integer now;
        Integer expired_at;
    }
}
