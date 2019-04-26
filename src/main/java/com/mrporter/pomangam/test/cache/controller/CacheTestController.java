package com.mrporter.pomangam.test.cache.controller;

import com.mrporter.pomangam.common.annotation.LogExecutionTime;
import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.test.cache.repository.CacheTestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tests/cache")
@Slf4j
public class CacheTestController {

    @Autowired
    CacheTestServiceImpl cacheTestService;

    @LogExecutionTime
    @GetMapping("/nocache/{id}")
    public User findByIdNoCache(@PathVariable String id){
        return cacheTestService.findByIdNoCache(id);
    }

    @LogExecutionTime
    @GetMapping("/cache/{id}")
    public User findByIdCache(@PathVariable String id){
        return cacheTestService.findByIdCache(id);
    }

    @LogExecutionTime
    @GetMapping("/refresh/{id}")
    public String refresh(@PathVariable String id){
        cacheTestService.refresh(id); // 캐시제거
        return "cache clear!";
    }

    @LogExecutionTime
    @GetMapping("/put/{id}")
    public User put(@PathVariable String id,
                      @RequestParam(name = "name") String name){
        return cacheTestService.put(id, name);
    }
}
