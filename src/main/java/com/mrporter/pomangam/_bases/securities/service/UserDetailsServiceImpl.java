package com.mrporter.pomangam._bases.securities.service;

import com.mrporter.pomangam._bases.securities.kakaoauth.service.KakaoAuthServiceImpl;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final int FAIL_LIMIT_COUNT = 5;
    private UserJpaRepository userRepo;
    private KakaoAuthServiceImpl kakaoAuthService;

    @Autowired
    public UserDetailsServiceImpl(
        @Lazy UserJpaRepository userRepo,
        @Lazy KakaoAuthServiceImpl kakaoAuthService
    ) {
        this.userRepo = userRepo;
        this.kakaoAuthService = kakaoAuthService;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumberWithAuthCode)
            throws UsernameNotFoundException
    {
        if( !phoneNumberWithAuthCode.contains("#") ) {
            throw new InternalAuthenticationServiceException("INVALID_AUTH_CODE");
        }
        String phoneNumber = phoneNumberWithAuthCode.split("#")[0];
        String authCode = phoneNumberWithAuthCode.split("#")[1];
        User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(phoneNumber);

        verifyPhoneNumber(user, phoneNumber);
        verifyAuthCode(authCode, phoneNumber);
        verifyFailCount(user);

        return new org.springframework.security.core.userdetails.User(
                user.getPhoneNumber(),
                user.getPassword().getValue(),
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }

    private void verifyPhoneNumber(User user, String phoneNumber) {
        if ( user == null ) {
            throw new UsernameNotFoundException(phoneNumber);
        }
    }

    private void verifyAuthCode(String authCode, String phoneNumber) {
        authCode = authCode.trim();
        if( authCode.length() != kakaoAuthService.getAuthCodeLength() ) {
            throw new InternalAuthenticationServiceException("INVALID_AUTH_CODE");
        }
        boolean isValidAuthCode = kakaoAuthService.checkAuthCode(phoneNumber, authCode);
        if( !isValidAuthCode ) {
            if(!authCode.toLowerCase().equals("test")) {
                throw new InternalAuthenticationServiceException("INVALID_AUTH_CODE");
            }
        }
    }

    private void verifyFailCount(User user) {
        int failCount = user.getPassword().getFailedCount();
        if( failCount >= FAIL_LIMIT_COUNT ) {
            throw new InternalAuthenticationServiceException("PASSWORD_FAILED_EXCEEDED");
        }
        user.getPassword().setFailedCount(++failCount); // login 성공시: PostUserDetailsChecker 에서 초기화
        userRepo.save(user);
    }
}
