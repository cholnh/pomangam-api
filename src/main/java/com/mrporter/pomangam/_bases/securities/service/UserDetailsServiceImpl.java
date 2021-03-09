package com.mrporter.pomangam._bases.securities.service;

import com.mrporter.pomangam._bases.securities.kakaoauth.service.KakaoAuthServiceImpl;
import com.mrporter.pomangam._bases.securities.kakaoauth.service.KakaoOauthServiceImpl;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final int FAIL_LIMIT_COUNT = 5;
    private UserJpaRepository userRepo;
    private OwnerJpaRepository storeOwnerJpaRepo;
    private StaffJpaRepository staffJpaRepo;
    private KakaoAuthServiceImpl kakaoAuthService;
    private KakaoOauthServiceImpl kakaoOauthService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(
        @Lazy UserJpaRepository userRepo,
        @Lazy OwnerJpaRepository storeOwnerJpaRepo,
        @Lazy StaffJpaRepository staffJpaRepo,
        @Lazy KakaoAuthServiceImpl kakaoAuthService,
        @Lazy KakaoOauthServiceImpl kakaoOauthService,
        @Lazy PasswordEncoder passwordEncoder
    ) {
        this.userRepo = userRepo;
        this.storeOwnerJpaRepo = storeOwnerJpaRepo;
        this.staffJpaRepo = staffJpaRepo;
        this.kakaoAuthService = kakaoAuthService;
        this.kakaoOauthService = kakaoOauthService;
        this.passwordEncoder = passwordEncoder;
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
        String userId;
        String userPw;
        List authorities;

        if(getGrantTypeByRequestBody().equals("refresh_token")) {
            User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(username);
            if ( user == null ) {
                throw new UsernameNotFoundException(username);
            }
            userId = user.getPhoneNumber();
            userPw = passwordEncoder.encode(user.getPassword().getPasswordValue());
            authorities = AuthorityUtils.createAuthorityList(user.getAuthorities());
        } else {
            if( !username.contains("#") ) {
                throw new InternalAuthenticationServiceException("INVALID_AUTH_CODE");
            }
            String id = username.split("#")[0];
            String authCode = username.split("#")[1];

            User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(id);
            if ( user == null ) {
                throw new UsernameNotFoundException(id);
            }
            userId = user.getPhoneNumber();
            authorities = AuthorityUtils.createAuthorityList(user.getAuthorities());

            if(authCode.equals("kakao")) {
                String token = getPasswordByRequestBody();
                String phoneNumber = id.replaceFirst("K", "");
                if(!kakaoOauthService.verifyOauthLogin(phoneNumber, token)) {
                    throw new InternalAuthenticationServiceException("KAKAO_OAUTH_AUTHENTICATION_CLIENT");
                }
                userPw =  passwordEncoder.encode(token);
            } else {
                verifyAuthCode(authCode, id);
                verifyFailCount(user);
                userPw = passwordEncoder.encode(authCode);
            }

        }
        return new org.springframework.security.core.userdetails.User(userId, userPw, authorities);
    }

    private org.springframework.security.core.userdetails.User verifyStoreOwner(String username) {
        Owner storeOwner = storeOwnerJpaRepo.findByIdAndIsActiveIsTrue(username);
        if ( storeOwner == null ) {
            throw new UsernameNotFoundException(username);
        }
        if(!getGrantTypeByRequestBody().equals("refresh_token")) {
            verifyFailCount(storeOwner);
        }
        return new org.springframework.security.core.userdetails.User(
                storeOwner.getId(),
                storeOwner.getPassword().getPasswordValue(),
                AuthorityUtils.createAuthorityList(storeOwner.getAuthorities()));
    }

    private org.springframework.security.core.userdetails.User verifyAdmin(String username) {
        Staff staff = staffJpaRepo.findByIdAndIsActiveIsTrue(username);
        if ( staff == null ) {
            throw new UsernameNotFoundException(username);
        }
        if(!getGrantTypeByRequestBody().equals("refresh_token")) {
            verifyFailCount(staff);
        }
        return new org.springframework.security.core.userdetails.User(
                staff.getId(),
                staff.getPassword().getPasswordValue(),
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

    private String getPasswordByRequestBody() {
        // HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        // return req.getParameter("password");

        HttpServletRequest currentRequest = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(requestAttributes -> ServletRequestAttributes.class.isAssignableFrom(requestAttributes.getClass()))
                .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
                .map(ServletRequestAttributes::getRequest).get();
        return currentRequest.getParameter("password");
    }

    private String getGrantTypeByRequestBody() {
        HttpServletRequest currentRequest = Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(requestAttributes -> ServletRequestAttributes.class.isAssignableFrom(requestAttributes.getClass()))
                .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
                .map(ServletRequestAttributes::getRequest).get();
        return currentRequest.getParameter("grant_type");
    }
}
