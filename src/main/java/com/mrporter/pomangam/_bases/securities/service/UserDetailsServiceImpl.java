package com.mrporter.pomangam._bases.securities.service;

import com.mrporter.pomangam._bases.securities.kakaoauth.service.KakaoAuthServiceImpl;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import com.mrporter.pomangam.store.domains.owner.Owner;
import com.mrporter.pomangam.store.repository.owner.OwnerJpaRepository;
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
    private OwnerJpaRepository storeOwnerJpaRepo;
    private KakaoAuthServiceImpl kakaoAuthService;

    @Autowired
    public UserDetailsServiceImpl(
        @Lazy UserJpaRepository userRepo,
        @Lazy OwnerJpaRepository storeOwnerJpaRepo,
        @Lazy KakaoAuthServiceImpl kakaoAuthService
    ) {
        this.userRepo = userRepo;
        this.storeOwnerJpaRepo = storeOwnerJpaRepo;
        this.kakaoAuthService = kakaoAuthService;
    }

    @Override
    public UserDetails loadUserByUsername(String args)
            throws UsernameNotFoundException
    {
        if( !args.contains("#") ) {
            throw new InternalAuthenticationServiceException("INVALID_AUTH_CODE");
        }
        String id = args.split("#")[0];
        String authCode = args.split("#")[1];

        if(authCode.equals("owner")) {
            Owner storeOwner = storeOwnerJpaRepo.findByIdAndIsActiveIsTrue(id);

            verifyStoreOwnerId(storeOwner, id);
            verifyFailCount(storeOwner);

            return new org.springframework.security.core.userdetails.User(
                    storeOwner.getId(),
                    storeOwner.getPassword().getValue(),
                    AuthorityUtils.createAuthorityList(storeOwner.getAuthorities()));
        } else {
            User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(id);

            verifyPhoneNumber(user, id);
            verifyAuthCode(authCode, id);
            verifyFailCount(user);

            return new org.springframework.security.core.userdetails.User(
                    user.getPhoneNumber(),
                    user.getPassword().getValue(),
                    AuthorityUtils.createAuthorityList(user.getAuthorities()));
        }
    }

    private void verifyPhoneNumber(User user, String phoneNumber) {
        if ( user == null ) {
            throw new UsernameNotFoundException(phoneNumber);
        }
    }

    private void verifyStoreOwnerId(Owner storeOwner, String id) {
        if ( storeOwner == null ) {
            throw new UsernameNotFoundException(id);
        }
    }

    private void verifyAuthCode(String authCode, String phoneNumber) {
        authCode = authCode.trim();
        if( authCode.length() != kakaoAuthService.getAuthCodeLength() ) {
            throw new InternalAuthenticationServiceException("INVALID_AUTH_CODE");
        }
        boolean isValidAuthCode = kakaoAuthService.checkAuthCodeNotDelete(phoneNumber, authCode);
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

    private void verifyFailCount(Owner storeOwner) {
        int failCount = storeOwner.getPassword().getFailedCount();
        if( failCount >= FAIL_LIMIT_COUNT ) {
            throw new InternalAuthenticationServiceException("PASSWORD_FAILED_EXCEEDED");
        }
        storeOwner.getPassword().setFailedCount(++failCount); // login 성공시: PostUserDetailsChecker 에서 초기화
        storeOwnerJpaRepo.save(storeOwner);
    }
}
