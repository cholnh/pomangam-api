package com.mrporter.pomangam.client.services.user;

import com.mrporter.pomangam._bases.securities.kakaoauth.service.KakaoAuthServiceImpl;
import com.mrporter.pomangam._bases.utils.formatter.PhoneNumberFormatter;
import com.mrporter.pomangam._bases.utils.reflection.ReflectionUtils;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.UserDto;
import com.mrporter.pomangam.client.domains.user.password.Password;
import com.mrporter.pomangam.client.domains.user.point.log.PointLog;
import com.mrporter.pomangam.client.domains.user.point.log.PointType;
import com.mrporter.pomangam.client.domains.user.point.rank.PointRank;
import com.mrporter.pomangam.client.repositories.order.OrderJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import com.mrporter.pomangam.client.repositories.user.point.log.PointLogJpaRepository;
import com.mrporter.pomangam.client.repositories.user.random_nickname.RandomNicknameJpaRepository;
import com.mrporter.pomangam.client.services.user.point.log.PointLogServiceImpl;
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
    PointLogServiceImpl pointLogService;
    KakaoAuthServiceImpl kakaoAuthService;

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        User user = userRepo.findByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        if(user == null) {
            return null;
        }
        UserDto userDto = UserDto.fromEntity(user);
        userDto.setUserPoint(pointLogService.findByIdxUser(userDto.getIdx()));
        userDto.getUserPointRank().setUserOrderCount((int) orderRepo.countByIsActiveIsTrue());
        userDto.getUserPointRank().setUserRecommendCount(0);
        return userDto;
    }

    @Override
    public Long findIdxByPhoneNumber(String phoneNumber) {
        return userRepo.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> dtos = UserDto.fromEntities(userRepo.findAll());
        for(UserDto userDto : dtos) {
            userDto.setUserPoint(pointLogService.findByIdxUser(userDto.getIdx()));
        }
        return dtos;
    }

    @Override
    public List<UserDto> findAll(Pageable pageable) {
        List<UserDto> dtos = UserDto.fromEntities(userRepo.findAll(pageable).getContent());
        for(UserDto userDto : dtos) {
            userDto.setUserPoint(pointLogService.findByIdxUser(userDto.getIdx()));
        }
        return dtos;
    }

    @Override
    @Transactional
    public UserDto saveUser(User user) {
        boolean isEmptyNickname = user.getNickname() == null || user.getNickname().isEmpty();
        if(isEmptyNickname) {
            user.setNickname(randomNickname());
        }
        try {
            user.getPassword().setFailedCount(0);
            user.getPassword().setPasswordValue(passwordEncoder.encode(user.getPassword().getPasswordValue()));
            user.setPhoneNumber(PhoneNumberFormatter.format(user.getPhoneNumber()));
            user.setPointRank(PointRank.builder().idx(1L).build());

            UserDto dto = UserDto.fromEntity(userRepo.save(user));

            String authCode = kakaoAuthService.getAuthCode();
            kakaoAuthService.saveAuthCode(dto.getPhoneNumber(), authCode);
            dto.setPhoneNumber(dto.getPhoneNumber()+"#"+authCode);
            dto.setUserPoint(0);
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        fetchedUser.getPassword().setPasswordValue(passwordEncoder.encode(password));
        fetchedUser.setModifyDate(LocalDateTime.now());

        userRepo.save(fetchedUser);

        UserDto dto = UserDto.fromEntity(fetchedUser);
        dto.setUserPoint(pointLogService.findByIdxUser(dto.getIdx()));
        return dto;
    }

    @Override
    @Transactional
    public UserDto patchUser(String phoneNumber, User user) {
        final User fetched = userRepo.findByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        if(fetched == null) {
            return null;
        }

        try {
            Password tempPassword = fetched.getPassword();
            ReflectionUtils.oldInstanceByNewInstance(fetched, user);
            fetched.setPassword(tempPassword);
            userRepo.save(fetched);

            UserDto dto = UserDto.fromEntity(fetched);
            dto.setUserPoint(pointLogService.findByIdxUser(dto.getIdx()));
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
    public int getPointByIdx(Long uIdx) {
        return pointLogService.findByIdxUser(uIdx);
    }

    @Override
    @Transactional
    public int plusPointByIdx(Long uIdx, int savedPoint, PointType pointType, Long oIdx) {
        // 포인트 로그
        PointLog pointLog = PointLog.builder()
                .idxUser(uIdx)
                .idxOrder(oIdx)
                .point(savedPoint)
                .pointType(pointType)
                .build();
        return pointLogService.save(pointLog).getPostPoint();
    }

    @Override
    @Transactional
    public int minusPointByIdx(Long uIdx, int usingPoint, PointType pointType, Long oIdx) {
        if(usingPoint < 0) {
            return 0;
        }
        // 포인트 로그
        PointLog pointLog = PointLog.builder()
                .idxUser(uIdx)
                .idxOrder(oIdx)
                .point(usingPoint)
                .pointType(pointType)
                .build();
        return pointLogService.save(pointLog).getPostPoint();
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
