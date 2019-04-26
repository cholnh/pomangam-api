package com.mrporter.pomangam.test.cache.repository;

import com.mrporter.pomangam.common.security.user.domain.User;

public interface CacheTestService {
    User findByIdNoCache(String id);
    User findByIdCache(String id);
    void refresh(String id);
    User put(String id, String name);
}
