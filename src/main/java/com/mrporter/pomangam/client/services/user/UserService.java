package com.mrporter.pomangam.client.services.user;

import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.point.log.PointType;
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

    Boolean isExistByNickname(String nickname);

    User patchUser(String phoneNumber, User user);

    Boolean deleteUser(String phoneNumber);

    int getPointByIdx(Long idx);

    int plusPointByIdx(Long idx, int point, PointType pointType);

    int minusPointByIdx(Long idx, int point, PointType pointType);
}
