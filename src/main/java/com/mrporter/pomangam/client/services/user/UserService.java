package com.mrporter.pomangam.client.services.user;

import com.mrporter.pomangam.client.domains.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User findByPhoneNumber(String phoneNumber);

    Long findIdxByPhoneNumber(String phoneNumber);

    List<User> findAll();

    List<User> findAll(Pageable pageable);

    User saveUser(User user);

    User updateUserPassword(String phoneNumber, String password);

    Boolean isExistByPhone(String phoneNumber);

    User patchUser(String phoneNumber, User user);

    Boolean deleteUser(String phoneNumber);

    int getPointByIdx(Long idx);

    int plusPointByIdx(Long idx, Integer point);

    int minusPointByIdx(Long idx, Integer point);
}
