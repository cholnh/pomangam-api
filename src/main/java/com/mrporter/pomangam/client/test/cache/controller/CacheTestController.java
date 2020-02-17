package com.mrporter.pomangam.client.test.cache.controller;

import com.mrporter.pomangam._bases.aspects.annotation.LogExecutionTime;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.test.cache.repository.CacheTestServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests/cache")
@Slf4j
@AllArgsConstructor
public class CacheTestController {

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
    @GetMapping("/cache")
    public List<User> findAllCache(){
        return cacheTestService.findAllCache();
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

    @LogExecutionTime
    @GetMapping("/put2")
    public void put2(){
        cacheTestService.put2();
    }
}
