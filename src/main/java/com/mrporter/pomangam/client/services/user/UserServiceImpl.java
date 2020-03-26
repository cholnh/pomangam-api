package com.mrporter.pomangam.client.services.user;

import com.mrporter.pomangam._bases.utils.formatter.PhoneNumberFormatter;
import com.mrporter.pomangam._bases.utils.reflection.ReflectionUtils;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.UserDto;
import com.mrporter.pomangam.client.domains.user.point.log.PointLog;
import com.mrporter.pomangam.client.domains.user.point.log.PointType;
import com.mrporter.pomangam.client.domains.user.point.rank.PointRank;
import com.mrporter.pomangam.client.repositories.order.OrderJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import com.mrporter.pomangam.client.repositories.user.point.log.PointLogJpaRepository;
import com.mrporter.pomangam.client.repositories.user.random_nickname.RandomNicknameJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    PasswordEncoder passwordEncoder;
    UserJpaRepository userRepo;
    RandomNicknameJpaRepository randomNicknameRepo;
    PointLogJpaRepository pointLogRepo;
    OrderJpaRepository orderRepo;

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        UserDto userDto = UserDto.fromEntity(userRepo.findByPhoneNumberAndIsActiveIsTrue(phoneNumber));
        userDto.getPointRank().setUserOrderCount((int) orderRepo.countByIsActiveIsTrue());
        userDto.getPointRank().setUserRecommendCount(0);
        return userDto;
    }

    @Override
    public Long findIdxByPhoneNumber(String phoneNumber) {
        return userRepo.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
    }

    @Override
    public List<UserDto> findAll() {
        return UserDto.fromEntities(userRepo.findAll());
    }

    @Override
    public List<UserDto> findAll(Pageable pageable) {
        return UserDto.fromEntities(userRepo.findAll(pageable).getContent());
    }

    @Override
    @Transactional
    public UserDto saveUser(User user) {
        boolean isEmptyNickname = user.getNickname() == null || user.getNickname().isEmpty();
        if(isEmptyNickname) {
            user.setNickname(randomNickname());
        }
        user.getPassword().setFailedCount(0);
        user.getPassword().setValue(passwordEncoder.encode(user.getPassword().getValue()));
        user.setPhoneNumber(PhoneNumberFormatter.format(user.getPhoneNumber()));
        user.setPointRank(PointRank.builder().idx(1L).build());

        return UserDto.fromEntity(userRepo.save(user));
    }

    @Override
    public Boolean isExistByPhone(String phoneNumber) {
        if(phoneNumber != null) {
            return userRepo.existsByPhoneNumber(phoneNumber);
        } else {
            return false;
        }
    }

    @Override
    public Boolean isExistByNickname(String nickname) {
        if(nickname != null) {
            return userRepo.existsByNickname(nickname);
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public UserDto updateUserPassword(String phoneNumber, String password) {
        final User fetchedUser = userRepo.findByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        if (fetchedUser == null) {
            return null;
        }
        fetchedUser.getPassword().setValue(passwordEncoder.encode(password));
        fetchedUser.setModifyDate(LocalDateTime.now());

        userRepo.save(fetchedUser);
        return UserDto.fromEntity(fetchedUser);
    }

    @Override
    @Transactional
    public UserDto patchUser(String phoneNumber, User user) {
        final User fetched = userRepo.findByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        if(fetched == null) {
            return null;
        }

        try {
            int point = fetched.getPoint();
            ReflectionUtils.oldInstanceByNewInstance(fetched, user);
            // fetched.setModifyDate(LocalDateTime.now());
            fetched.setPoint(point); // 포인트는 외부 patch 로직으로 인해 변경 불가능
            userRepo.save(fetched);
            return UserDto.fromEntity(fetched);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public Boolean deleteUser(String phoneNumber) {
        final User fetchedUser = userRepo.findByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        if (fetchedUser == null) {
            return false;
        } else {
            userRepo.delete(fetchedUser);
            return true;
        }
    }

    @Override
    public int getPointByIdx(Long idx) {
        return userRepo.getOne(idx).getPoint();
    }

    @Override
    @Transactional
    public int plusPointByIdx(Long uIdx, int savedPoint, PointType pointType) {
        User user = userRepo.getOne(uIdx);
        int userPoint = user.getPoint() + savedPoint;
        user.setPoint(userPoint);
        userRepo.save(user);

        // 포인트 로그
        PointLog pointLog = PointLog.builder()
                .idxUser(uIdx)
                .point(savedPoint)
                .postPoint(userPoint)
                .pointType(pointType)
                .build();
        pointLogRepo.save(pointLog);
        return userPoint;
    }

    @Override
    @Transactional
    public int minusPointByIdx(Long uIdx, int usingPoint, PointType pointType) {
        if(usingPoint < 0) {
            return 0;
        }
        User user = userRepo.getOne(uIdx);
        int userPoint = user.getPoint() - usingPoint;
        user.setPoint(userPoint);
        userRepo.save(user);

        // 포인트 로그
        PointLog pointLog = PointLog.builder()
                .idxUser(uIdx)
                .point(usingPoint)
                .postPoint(userPoint)
                .pointType(pointType)
                .build();
        pointLogRepo.save(pointLog);

        return userPoint;
    }

    private String randomNickname() {
        String nick, first, second;
        int count = 0;
        do {
            first = randomNicknameRepo.findFirstByRandomAndIsActiveIsTrue();
            second = randomNicknameRepo.findSecondByRandomAndIsActiveIsTrue();
            if(first == null || first.isEmpty() || second == null || second.isEmpty()) {
                nick = "포만이" + new Random().nextInt(9999999);
            } else {
                nick = first + second;
            }
            if(count++ != 0) {
                nick += count;
            }
        } while(isExistByNickname(nick));
        return nick;
    }
}
