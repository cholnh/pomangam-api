package com.mrporter.pomangam.admin.services.user;

import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.point.log.PointType;
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

    int getPointByIdx(Long idx);

    int plusPointByIdx(Long uIdx, int savedPoint, PointType pointType);

    int minusPointByIdx(Long uIdx, int usingPoint, PointType pointType);
}
