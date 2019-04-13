package com.mrporter.pomangam.common.security.user.service;

import com.mrporter.pomangam.common.security.authority.domain.Authority;
import com.mrporter.pomangam.common.security.authority.repository.AuthorityJpaRepository;
import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.repository.UserJpaRepository;
import com.mrporter.pomangam.common.util.time.CustomTime;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    PasswordEncoder passwordEncoder;
    UserJpaRepository userJpaRepository;
    AuthorityJpaRepository authorityJpaRepository;

    @Override
    public User findById(String id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userJpaRepository.findAll();
    }

    @Override
    public Page<User> findAllUsers(Pageable pageable) {
        return userJpaRepository.findAll(pageable);
    }

    @Override
    public User saveUser(User user) {
        authorityJpaRepository.save(new Authority(user.getId(), "ROLE_USER"));

        user.setRegister_date(CustomTime.curTimestampSql());
        user.setPw(passwordEncoder.encode(user.getPw()));

        return userJpaRepository.save(user);
    }

    @Override
    public Boolean isUserExist(User user) {
        if(user.getId() != null) {
            final User existingUser = userJpaRepository.findById(user.getId());
            return existingUser == null ? false : true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean isUserExist(String id) {
        if(id != null) {
            final User existingUser = userJpaRepository.findById(id);
            return existingUser == null ? false : true;
        } else {
            return false;
        }
    }

    @Override
    public User updateUser(String id, User user) {
        final User fetchedUser = userJpaRepository.findById(id);
        if (fetchedUser == null) {
            return null;
        }
        fetchedUser.setDelivery_site_idx(user.getDelivery_site_idx());
        fetchedUser.setId(user.getId());
        fetchedUser.setPw(passwordEncoder.encode(user.getPw()));
        fetchedUser.setName(user.getName());
        fetchedUser.setNickname(user.getNickname());
        fetchedUser.setGender(user.getGender());
        fetchedUser.setYear_of_birth(user.getYear_of_birth());
        fetchedUser.setMonth_of_birth(user.getMonth_of_birth());
        fetchedUser.setDays_of_birth(user.getDays_of_birth());
        fetchedUser.setPhone_number(user.getPhone_number());
        fetchedUser.setState_active(user.getState_active());

        //fetchedUser.setRegister_date(user.getRegister_date());
        fetchedUser.setModify_date(CustomTime.curTimestampSql());

        fetchedUser.setPoint(user.getPoint());
        userJpaRepository.save(fetchedUser);
        return fetchedUser;
    }

    @Override
    public User patchUser(String id, User user) {
        final User fetchedUser = userJpaRepository.findById(id);
        if (fetchedUser == null) {
            return null;
        }

        if (user.getDelivery_site_idx() != null) {
            fetchedUser.setDelivery_site_idx(user.getDelivery_site_idx());
        }
        if (user.getId() != null) {
            fetchedUser.setId(user.getId());
        }
        if (user.getPw() != null) {
            fetchedUser.setPw(passwordEncoder.encode(user.getPw()));
            //fetchedUser.setPw(user.getPw());
        }
        if (user.getName() != null) {
            fetchedUser.setName(user.getName());
        }
        if (user.getNickname() != null) {
            fetchedUser.setNickname(user.getNickname());
        }
        if (user.getGender() != null) {
            fetchedUser.setGender(user.getGender());
        }
        if (user.getYear_of_birth() != null) {
            fetchedUser.setYear_of_birth(user.getYear_of_birth());
        }
        if (user.getMonth_of_birth() != null) {
            fetchedUser.setMonth_of_birth(user.getMonth_of_birth());
        }
        if (user.getDays_of_birth() != null) {
            fetchedUser.setDays_of_birth(user.getDays_of_birth());
        }
        if (user.getPhone_number() != null) {
            fetchedUser.setPhone_number(user.getPhone_number());
        }
        if (user.getState_active() != null) {
            fetchedUser.setState_active(user.getState_active());
        }

        //if (user.getRegister_date() != null) {
        //    fetchedUser.setRegister_date(user.getRegister_date());
        //}
        fetchedUser.setModify_date(CustomTime.curTimestampSql());

        if (user.getPoint() != null) {
            fetchedUser.setPoint(user.getPoint());
        }
        userJpaRepository.save(fetchedUser);
        return fetchedUser;
    }

    @Override
    public Boolean deleteUser(String id) {
        final User fetchedUser = userJpaRepository.findById(id);
        if (fetchedUser == null) {
            return false;
        } else {
            userJpaRepository.delete(fetchedUser);
            return true;
        }
    }
}