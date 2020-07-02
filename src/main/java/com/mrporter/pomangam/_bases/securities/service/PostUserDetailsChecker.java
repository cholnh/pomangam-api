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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Service;

@Service
public class PostUserDetailsChecker implements UserDetailsChecker {

    private UserJpaRepository userRepo;
    private OwnerJpaRepository storeOwnerJpaRepo;
    private StaffJpaRepository staffJpaRepo;
    private KakaoAuthServiceImpl kakaoAuthService;

    @Autowired
    public PostUserDetailsChecker(
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
    public void check(UserDetails toCheck) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String clientName = authentication.getName();
            switch (clientName) {
                case "guest":
                case "login": checkUser(toCheck); break;
                case "store_owner": checkStoreOwner(toCheck); break;
                case "staff": checkStaff(toCheck); break;
                default: throw new InternalAuthenticationServiceException("ANONYMOUS_AUTHENTICATION_CLIENT");
            }
        }
    }

    void checkUser(UserDetails toCheck) {
        User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(toCheck.getUsername());
        if(user != null) {
            user.getPassword().setFailedCount(0);
            userRepo.save(user);
            kakaoAuthService.deleteAuthCode(user.getPhoneNumber());
        }
    }

    void checkStoreOwner(UserDetails toCheck) {
        Owner storeOwner = storeOwnerJpaRepo.findByIdAndIsActiveIsTrue(toCheck.getUsername());
        if(storeOwner != null) {
            storeOwner.getPassword().setFailedCount(0);
            storeOwnerJpaRepo.save(storeOwner);
        }
    }

    void checkStaff(UserDetails toCheck) {
        Staff staff = staffJpaRepo.findByIdAndIsActiveIsTrue(toCheck.getUsername());
        if(staff != null) {
            staff.getPassword().setFailedCount(0);
            staffJpaRepo.save(staff);
        }
    }
}
