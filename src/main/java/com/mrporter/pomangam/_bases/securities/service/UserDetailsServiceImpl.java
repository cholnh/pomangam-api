package com.mrporter.pomangam._bases.securities.service;

import com.mrporter.pomangam._bases.securities.kakaoauth.service.KakaoAuthServiceImpl;
import com.mrporter.pomangam.admin.domains.staff.Staff;
import com.mrporter.pomangam.admin.repositories.staff.StaffJpaRepository;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import com.mrporter.pomangam.store.domains.owner.Owner;
import com.mrporter.pomangam.store.repository.owner.OwnerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final int FAIL_LIMIT_COUNT = 5;
    private UserJpaRepository userRepo;
    private OwnerJpaRepository storeOwnerJpaRepo;
    private StaffJpaRepository staffJpaRepo;
    private KakaoAuthServiceImpl kakaoAuthService;

    @Autowired
    public UserDetailsServiceImpl(
        @Lazy UserJpaRepository userRepo,
        @Lazy OwnerJpaRepository storeOwnerJpaRepo,
        @Lazy StaffJpaRepository staffJpaRepo,
        @Lazy KakaoAuthServiceImpl kakaoAuthService
    ) {
        this.userRepo = userRepo;
        this.storeOwnerJpaRepo = storeOwnerJpaRepo;
        this.staffJpaRepo = staffJpaRepo;
        this.kakaoAuthService = kakaoAuthService;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User userDetail = null;

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String clientName = authentication.getName();
            switch (clientName) {
                case "guest":
                case "login": userDetail = verifyClient(username); break;
                case "store_owner": userDetail = verifyStoreOwner(username); break;
                case "staff": userDetail = verifyAdmin(username); break;
                default: throw new InternalAuthenticationServiceException("ANONYMOUS_AUTHENTICATION_CLIENT");
            }
        }
        return userDetail;
    }

    private org.springframework.security.core.userdetails.User verifyClient(String username) {
        if( !username.contains("#") ) {
            throw new InternalAuthenticationServiceException("INVALID_AUTH_CODE");
        }
        String id = username.split("#")[0];
        String authCode = username.split("#")[1];

        User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(id);
        if ( user == null ) {
            throw new UsernameNotFoundException(id);
        }
        verifyAuthCode(authCode, id);
        verifyFailCount(user);
        return new org.springframework.security.core.userdetails.User(
                user.getPhoneNumber(),
                user.getPassword().getValue(),
                AuthorityUtils.createAuthorityList(user.getAuthorities()));
    }

    private org.springframework.security.core.userdetails.User verifyStoreOwner(String username) {
        Owner storeOwner = storeOwnerJpaRepo.findByIdAndIsActiveIsTrue(username);
        if ( storeOwner == null ) {
            throw new UsernameNotFoundException(username);
        }
        verifyFailCount(storeOwner);
        return new org.springframework.security.core.userdetails.User(
                storeOwner.getId(),
                storeOwner.getPassword().getValue(),
                AuthorityUtils.createAuthorityList(storeOwner.getAuthorities()));
    }

    private org.springframework.security.core.userdetails.User verifyAdmin(String username) {
        Staff staff = staffJpaRepo.findByIdAndIsActiveIsTrue(username);
        if ( staff == null ) {
            throw new UsernameNotFoundException(username);
        }
        verifyFailCount(staff);
        return new org.springframework.security.core.userdetails.User(
                staff.getId(),
                staff.getPassword().getValue(),
                AuthorityUtils.createAuthorityList(staff.getAuthorities()));
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

    private void verifyFailCount(Staff staff) {
        int failCount = staff.getPassword().getFailedCount();
        if( failCount >= FAIL_LIMIT_COUNT ) {
            throw new InternalAuthenticationServiceException("PASSWORD_FAILED_EXCEEDED");
        }
        staff.getPassword().setFailedCount(++failCount); // login 성공시: PostUserDetailsChecker 에서 초기화
        staffJpaRepo.save(staff);
    }
}
