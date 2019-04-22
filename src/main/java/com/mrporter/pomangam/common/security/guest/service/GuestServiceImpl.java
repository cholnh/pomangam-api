package com.mrporter.pomangam.common.security.guest.service;

import com.mrporter.pomangam.common.security.guest.domain.Guest;
import com.mrporter.pomangam.common.security.guest.repository.GuestJpaRepository;
import com.mrporter.pomangam.common.util.time.CustomTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {

    GuestJpaRepository guestJpaRepository;

    @Override
    public int generateGuest() {
        Guest guest = guestJpaRepository.save(new Guest(CustomTime.curTimestampSql()));
        return guest.getIdx();
    }
}
