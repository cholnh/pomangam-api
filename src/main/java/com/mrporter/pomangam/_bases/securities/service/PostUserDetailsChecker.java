package com.mrporter.pomangam._bases.securities.service;

import com.mrporter.pomangam._bases.securities.kakaoauth.service.KakaoAuthServiceImpl;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Service;

@Service
public class PostUserDetailsChecker implements UserDetailsChecker {

    private UserJpaRepository userRepo;
    private KakaoAuthServiceImpl kakaoAuthService;

    @Autowired
    public PostUserDetailsChecker(
        @Lazy UserJpaRepository userRepo,
        @Lazy KakaoAuthServiceImpl kakaoAuthService
    ) {
        this.userRepo = userRepo;
        this.kakaoAuthService = kakaoAuthService;
    }

    @Override
    public void check(UserDetails toCheck) {
        User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(toCheck.getUsername());
        user.getPassword().setFailedCount(0);
        userRepo.save(user);

        kakaoAuthService.deleteAuthCode(user.getPhoneNumber());
    }
}
