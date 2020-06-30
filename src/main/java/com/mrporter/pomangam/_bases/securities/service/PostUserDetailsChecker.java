package com.mrporter.pomangam._bases.securities.service;

import com.mrporter.pomangam._bases.securities.kakaoauth.service.KakaoAuthServiceImpl;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import com.mrporter.pomangam.store.domains.owner.Owner;
import com.mrporter.pomangam.store.repository.owner.OwnerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Service;

@Service
public class PostUserDetailsChecker implements UserDetailsChecker {

    private UserJpaRepository userRepo;
    private OwnerJpaRepository storeOwnerJpaRepo;
    private KakaoAuthServiceImpl kakaoAuthService;

    @Autowired
    public PostUserDetailsChecker(
        @Lazy UserJpaRepository userRepo,
        @Lazy OwnerJpaRepository storeOwnerJpaRepo,
        @Lazy KakaoAuthServiceImpl kakaoAuthService
    ) {
        this.userRepo = userRepo;
        this.storeOwnerJpaRepo = storeOwnerJpaRepo;
        this.kakaoAuthService = kakaoAuthService;
    }

    @Override
    public void check(UserDetails toCheck) {
        User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(toCheck.getUsername());
        if(user == null) {
            Owner storeOwner = storeOwnerJpaRepo.findByIdAndIsActiveIsTrue(toCheck.getUsername());
            storeOwner.getPassword().setFailedCount(0);
            storeOwnerJpaRepo.save(storeOwner);
        } else {
            user.getPassword().setFailedCount(0);
            userRepo.save(user);
            kakaoAuthService.deleteAuthCode(user.getPhoneNumber());
        }
    }
}
