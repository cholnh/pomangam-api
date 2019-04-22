package com.mrporter.pomangam.common.security.user.service;

import com.mrporter.pomangam.common.security.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    /**
     * To return a user object fetched by ID
     *
     * @param id user ID
     * @return User object
     */
    User findById(String id);

    User findByPhoneNumber(String phone_number);

    /**
     * @return the list of all users
     */
    List<User> findAllUsers();

    /**
     * @return the list of all users by pageable
     */
    Page<User> findAllUsers(Pageable pageable);

    /**
     * @param user User entity to be saved
     */
    User saveUser(User user);

    User updateUserPw(String id, String pw);

    /**
     * @param user User entity to check existence
     * @return true if exist; otherwise, return false
     */
    Boolean isUserExist(User user);
    Boolean isUserExist(String id);
    /**
     * @param user updated user entity
     * @return updated user entity
     */
    User updateUser(String id, User user);

    Boolean isUserExistByPhoneNumber(String phone_number);
    Boolean isUserExistByIdAndPhoneNumber(String id, String phone_number);

    /**
     * @param user updated user entity
     * @return patched user entity
     */
    User patchUser(String id, User user);

    /**
     * @param id user ID to be deleted
     * @return true, if deleted; otherwise, return false
     */
    Boolean deleteUser(String id);

    int getPointByIdx(Integer idx);

    int plusPointByIdx(Integer idx, Integer point);

    int minusPointByIdx(Integer idx, Integer point);
}
