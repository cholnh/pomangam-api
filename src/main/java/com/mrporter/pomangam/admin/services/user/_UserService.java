package com.mrporter.pomangam.admin.services.user;

import com.mrporter.pomangam.client.domains.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface _UserService {
    User findByPhoneNumber(String phoneNumber);

    List<User> findAll();

    List<User> findAll(Pageable pageable);

    User saveUser(User user);

    User updateUserPw(String phoneNumber, String pw);

    Boolean isExistByPhone(String phoneNumber);

    User patchUser(String phoneNumber, User user);

    Boolean deleteUser(String phoneNumber);

    int getPointByIdx(Integer idx);

    int plusPointByIdx(Integer idx, Integer point);

    int minusPointByIdx(Integer idx, Integer point);
}
