package com.mrporter.pomangam._bases.utils.security;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class Ip {
    public static String getInfo() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String ip = request.getHeader("X-FORWARDED-FOR");
            if(ip == null) {
                ip = request.getRemoteAddr();
            }
            return ip;
        } catch (Exception e) {
            return "unknown ip";
        }
    }

    public static boolean isBootpayIp() {
        String ip = getInfo();
        System.out.println(ip);
        for(int i=1; i<256; i++) {
            String bootpayIp = "223.130.82." + i;
            if(bootpayIp.equals(ip)) {
                return true;
            }
        }
        return false;
    }
}
