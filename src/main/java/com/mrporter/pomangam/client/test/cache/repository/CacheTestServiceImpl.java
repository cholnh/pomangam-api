package com.mrporter.pomangam.client.test.cache.repository;

import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class CacheTestServiceImpl implements CacheTestService {

    UserJpaRepository userJpaRepository;

    @Override
    public User findByIdNoCache(String id) {
        slowQuery(2000);
        return userJpaRepository.findByPhoneNumber(id);
    }

    @Cacheable(value = "test2")
    public List<User> findAllCache() {
        slowQuery(2000);
        return userJpaRepository.findAll();
    }

    @Override
    @Cacheable(value = "test", key="#id")
    public User findByIdCache(String id) {
        slowQuery(2000);
        return userJpaRepository.findByPhoneNumber(id);
    }

    @Override
    @CacheEvict(value = "test", key="#id")
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

    @CachePut(value = "test", key="#id")
    public User put(String id, String name) {
        User user = userJpaRepository.findByPhoneNumber(id);
        user.setName(name);
        return userJpaRepository.save(user);
    }

    @CachePut(value = "test2")
    public void put2() {
        log.info("cache put 2");
    }
}
