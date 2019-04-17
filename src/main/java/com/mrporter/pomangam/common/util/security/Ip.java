package com.mrporter.pomangam.common.util.security;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class Ip {
    public static String getInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("X-FORWARDED-FOR");
        if(ip == null) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
