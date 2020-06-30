package com.mrporter.pomangam.test.data.storeOwner;

import com.mrporter.pomangam.client.domains.user.Sex;
import com.mrporter.pomangam.client.domains.user.password.Password;
import com.mrporter.pomangam.store.domains.owner.Owner;
import com.mrporter.pomangam.store.services.owner.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class StoreOwnerData {

    @Autowired
    OwnerServiceImpl ownerService;

    @Transactional
    public void of(Long idx, Long fIdx, String id, String password, String name, String phoneNumber, Sex sex, LocalDate birth, String authorities) {
        Owner owner = Owner.builder()
                .idx(idx)
                .id(id)
                .password(Password.builder()
                        .failedCount(0)
                        .value(password)
                        .build())
                .name(name)
                .phoneNumber(phoneNumber)
                .sex(sex)
                .birth(birth)
                .idxFcmToken(fIdx)
                .authorities(authorities)
                .build();
        ownerService.saveOwner(owner);
    }
}