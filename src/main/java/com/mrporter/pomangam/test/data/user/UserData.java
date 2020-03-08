package com.mrporter.pomangam.test.data.user;

import com.mrporter.pomangam.client.domains.deliverysite.detail.DeliveryDetailSite;
import com.mrporter.pomangam.client.domains.user.Sex;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.password.Password;
import com.mrporter.pomangam.client.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class UserData {

    @Autowired
    UserServiceImpl userService;

    @Transactional
    public void of(Long idx, Long fIdx, Long ddIdx, String phoneNumber, String password, String name, String nickname, Sex sex, LocalDate birth, String authorities) {
        User user = User.builder()
                .idx(idx)
                .deliveryDetailSite(DeliveryDetailSite.builder().idx(ddIdx).build())
                .phoneNumber(phoneNumber)
                .password(Password.builder()
                        .value(password)
                        .build())
                .name(name)
                .nickname(nickname)
                .sex(sex)
                .birth(birth)
                .idxFcmToken(fIdx)
                .authorities(authorities)
                .build();
        userService.saveUser(user);
    }
}