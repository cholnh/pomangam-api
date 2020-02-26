package com.mrporter.pomangam.admin.services.user;

import com.mrporter.pomangam._bases.utils.formatter.PhoneNumberFormatter;
import com.mrporter.pomangam._bases.utils.reflection.ReflectionUtils;
import com.mrporter.pomangam.admin.repositories.user._UserJpaRepository;
import com.mrporter.pomangam.client.domains.user.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class _UserServiceImpl implements _UserService {

    PasswordEncoder passwordEncoder;
    _UserJpaRepository userJpaRepository;

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
    public int getPointByIdx(Long idx) {
        return userJpaRepository.getOne(idx).getPoint();
    }

    @Override
    public int plusPointByIdx(Long idx, Integer point) {
        User user = userJpaRepository.getOne(idx);
        int p = user.getPoint() + point;
        user.setPoint(p);
        userJpaRepository.save(user);
        return p;
    }

    @Override
    public int minusPointByIdx(Long idx, Integer point) {
        User user = userJpaRepository.getOne(idx);
        int p = user.getPoint() - point;
        user.setPoint(p);
        userJpaRepository.save(user);
        return p;
    }
}
