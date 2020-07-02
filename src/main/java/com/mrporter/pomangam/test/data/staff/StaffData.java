package com.mrporter.pomangam.test.data.staff;

import com.mrporter.pomangam.admin.domains.staff.Staff;
import com.mrporter.pomangam.admin.services.staff.StaffServiceImpl;
import com.mrporter.pomangam.client.domains.user.Sex;
import com.mrporter.pomangam.client.domains.user.password.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
public class StaffData {
    @Autowired
    StaffServiceImpl staffService;

    @Transactional
    public void of(Long idx, Long fIdx, String id, String password, String name, String phoneNumber, Sex sex, LocalDate birth, String authorities) {
        Staff staff = Staff.builder()
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
        staffService.saveStaff(staff);
    }
}
