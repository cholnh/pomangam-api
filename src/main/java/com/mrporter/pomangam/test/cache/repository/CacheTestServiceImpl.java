package com.mrporter.pomangam.test.cache.repository;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.repository.UserJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CacheTestServiceImpl implements CacheTestService {

    @Autowired
    UserJpaRepository userJpaRepository;

    @Override
    public User findByIdNoCache(String id) {
        slowQuery(2000);
        User user = userJpaRepository.findById(id);
        return user;
    }

    @Override
    @Cacheable(value="findUserCache", key="#id")
    public User findByIdCache(String id) {
        slowQuery(2000);
        User user = userJpaRepository.findById(id);
        return user;
    }

    @Override
    @CacheEvict(value = "findUserCache", key="#id")
    public void refresh(String id) {
        log.info(id + "의 Cache Clear!");
    }

    // 빅쿼리를 돌린다는 가정
    private void slowQuery(long seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    @CachePut(value = "findUserCache", key="#id")
    public User put(String id, String name) {
        User user = userJpaRepository.findById(id);
        user.setName(name);
        return userJpaRepository.save(user);
    }
}
