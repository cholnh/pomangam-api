package com.mrporter.pomangam._bases.securities.service;

import com.mrporter.pomangam._bases.securities.kakaoauth.service.KakaoAuthServiceImpl;
import com.mrporter.pomangam._bases.securities.service.exception.AuthCodeNotFoundException;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    public UserDetailsServiceImpl(
        @Lazy UserServiceImpl userService,
        @Lazy KakaoAuthServiceImpl kakaoAuthService
    ) {
        this.userService = userService;
        this.kakaoAuthService = kakaoAuthService;
    }

    private UserServiceImpl userService;
    private KakaoAuthServiceImpl kakaoAuthService;

    @Override
    public UserDetails loadUserByUsername(String phoneNumberWithAuthCode)
            throws UsernameNotFoundException
    {
        if(!phoneNumberWithAuthCode.contains("#")) {
            throw new UsernameNotFoundException(phoneNumberWithAuthCode);
        }
        String phoneNumber = phoneNumberWithAuthCode.split("#")[0];
        String authCode = phoneNumberWithAuthCode.split("#")[1];

        // 유저 id 인증
        User user = userService.findByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new UsernameNotFoundException(phoneNumber);
        }

        // auth code 인증
        authCode = authCode.trim();
        if(authCode.length() != kakaoAuthService.getAuthCodeLength()) {
            throw new UsernameNotFoundException(phoneNumberWithAuthCode);
        }
        boolean isValidAuthCode = kakaoAuthService.checkAuthCode(phoneNumber, authCode);
        if(!isValidAuthCode) {
            throw new UsernameNotFoundException(phoneNumberWithAuthCode);
        }

        // 유저 정보 인증, 전달
        return new org.springframework.security.core.userdetails.User(
                user.getPhoneNumber(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
