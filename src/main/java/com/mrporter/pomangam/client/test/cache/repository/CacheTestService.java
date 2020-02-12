package com.mrporter.pomangam.client.test.cache.repository;

import com.mrporter.pomangam.client.domains.user.User;

public interface CacheTestService {
    User findByIdNoCache(String id);
    User findByIdCache(String id);
    void refresh(String id);
    User put(String id, String name);
}
