package com.mrporter.pomangam.client.services.user;

import com.mrporter.pomangam._bases.utils.formatter.PhoneNumberFormatter;
import com.mrporter.pomangam._bases.utils.reflection.ReflectionUtils;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.UserDto;
import com.mrporter.pomangam.client.repositories._bases.RandomNicknameJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    PasswordEncoder passwordEncoder;
    UserJpaRepository userRepository;
    RandomNicknameJpaRepository randomNicknameRepository;

    public List<UserDto> findRecentlyRegistered(int limit) {
        return UserDto.fromEntities(userRepository.findRecentlyRegistered(limit));
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    private String randomNickname() {
        String nick, first, second;
        int count = 0;
        do {
            first = randomNicknameRepository.findFirstByRandom();
            second = randomNicknameRepository.findSecondByRandom();
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

    @Override
    public User saveUser(User user) {
        boolean isEmptyNickname = user.getNickname() == null || user.getNickname().isEmpty();
        if(isEmptyNickname) {
            user.setNickname(randomNickname());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPhoneNumber(PhoneNumberFormatter.format(user.getPhoneNumber()));
        return userRepository.save(user);
    }

    @Override
    public Boolean isExistByPhone(String phoneNumber) {
        if(phoneNumber != null) {
            final User existingUser = userRepository.findByPhoneNumber(phoneNumber);
            return existingUser != null;
        } else {
            return false;
        }
    }

    public Boolean isExistByNickname(String nickname) {
        if(nickname != null) {
            return userRepository.existsByNickname(nickname);
        } else {
            return false;
        }
    }

    @Override
    public User updateUserPassword(String phoneNumber, String password) {
        final User fetchedUser = userRepository.findByPhoneNumber(phoneNumber);
        if (fetchedUser == null) {
            return null;
        }

        fetchedUser.setPassword(passwordEncoder.encode(password));
        fetchedUser.setModifyDate(LocalDateTime.now());

        userRepository.save(fetchedUser);
        return fetchedUser;
    }

    @Override
    public User patchUser(String phoneNumber, User user) {
        final User fetched = userRepository.findByPhoneNumber(phoneNumber);
        if(fetched == null) {
            return null;
        }

        try {
            int point = fetched.getPoint();
            ReflectionUtils.oldInstanceByNewInstance(fetched, user);
            fetched.setModifyDate(LocalDateTime.now());
            fetched.setPoint(point); // 포인트는 외부 patch 로직으로 인해 변경 불가능
            userRepository.save(fetched);
            return fetched;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteUser(String phoneNumber) {
        final User fetchedUser = userRepository.findByPhoneNumber(phoneNumber);
        if (fetchedUser == null) {
            return false;
        } else {
            userRepository.delete(fetchedUser);
            return true;
        }
    }

    @Override
    public int getPointByIdx(Long idx) {
        return userRepository.getOne(idx).getPoint();
    }

    @Override
    public int plusPointByIdx(Long idx, Integer point) {
        User user = userRepository.getOne(idx);
        int p = user.getPoint() + point;
        user.setPoint(p);
        userRepository.save(user);
        return p;
    }

    @Override
    public int minusPointByIdx(Long idx, Integer point) {
        User user = userRepository.getOne(idx);
        int p = user.getPoint() - point;
        user.setPoint(p);
        userRepository.save(user);
        return p;
    }


}
