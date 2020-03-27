package com.mrporter.pomangam.admin.services.user;

import com.mrporter.pomangam._bases.utils.formatter.PhoneNumberFormatter;
import com.mrporter.pomangam._bases.utils.reflection.ReflectionUtils;
import com.mrporter.pomangam.admin.repositories.user._UserJpaRepository;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.point.log.PointLog;
import com.mrporter.pomangam.client.domains.user.point.log.PointType;
import com.mrporter.pomangam.client.services.user.point.log.PointLogServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class _UserServiceImpl implements _UserService {

    PasswordEncoder passwordEncoder;
    _UserJpaRepository userJpaRepository;
    PointLogServiceImpl pointLogService;

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userJpaRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public List<User> findAll(Pageable pageable) {
        return userJpaRepository.findAll(pageable).getContent();
    }

    @Override
    public User saveUser(User user) {
        user.getPassword().setValue(passwordEncoder.encode(user.getPassword().getValue()));
        user.setPhoneNumber(PhoneNumberFormatter.format(user.getPhoneNumber()));
        return userJpaRepository.save(user);
    }

    @Override
    public Boolean isExistByPhone(String phoneNumber) {
        if(phoneNumber != null) {
            final User existingUser = userJpaRepository.findByPhoneNumber(phoneNumber);
            return existingUser == null ? false : true;
        } else {
            return false;
        }
    }

    @Override
    public User updateUserPw(String phoneNumber, String pw) {
        final User fetchedUser = userJpaRepository.findByPhoneNumber(phoneNumber);
        if (fetchedUser == null) {
            return null;
        }
        fetchedUser.getPassword().setValue(passwordEncoder.encode(pw));
        fetchedUser.setModifyDate(LocalDateTime.now());

        userJpaRepository.save(fetchedUser);
        return fetchedUser;
    }

    @Override
    public User patchUser(String phoneNumber, User user) {
        final User fetched = userJpaRepository.findByPhoneNumber(phoneNumber);
        if(fetched == null) {
            return null;
        }

        try {
            ReflectionUtils.oldInstanceByNewInstance(fetched, user);
            fetched.setModifyDate(LocalDateTime.now());

            userJpaRepository.save(fetched);
            return fetched;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean deleteUser(String phoneNumber) {
        final User fetchedUser = userJpaRepository.findByPhoneNumber(phoneNumber);
        if (fetchedUser == null) {
            return false;
        } else {
            userJpaRepository.delete(fetchedUser);
            return true;
        }
    }

    @Override
    public int getPointByIdx(Long uIdx) {
        return pointLogService.findByIdxUser(uIdx);
    }

    @Transactional
    public int plusPointByIdx(Long uIdx, int savedPoint, PointType pointType) {
        // 포인트 로그
        PointLog pointLog = PointLog.builder()
                .idxUser(uIdx)
                .point(savedPoint)
                .pointType(pointType)
                .build();
        return pointLogService.save(pointLog).getPostPoint();
    }

    @Transactional
    public int minusPointByIdx(Long uIdx, int usingPoint, PointType pointType) {
        if(usingPoint < 0) {
            return 0;
        }
        // 포인트 로그
        PointLog pointLog = PointLog.builder()
                .idxUser(uIdx)
                .point(usingPoint)
                .pointType(pointType)
                .build();
        return pointLogService.save(pointLog).getPostPoint();
    }
}
