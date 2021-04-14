package com.mrporter.pomangam.client.test.cache.controller;

import com.mrporter.pomangam._bases.aspects.annotation.LogExecutionTime;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.test.cache.repository.CacheTestServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CacheTestController", description = "Cache Test API")
@RestController
@RequestMapping("/tests/cache")
@Slf4j
@AllArgsConstructor
public class CacheTestController {

    CacheTestServiceImpl cacheTestService;

    @Operation(summary = "조회 (캐시x)", description = "id를 이용하여 조회합니다.", responses = {
        @ApiResponse(responseCode = "200", description = "회원 조회 성공", content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = Exception.class)))})
    @LogExecutionTime
    @GetMapping("/nocache/{id}")
    public User findByIdNoCache(
            @Parameter(name = "id", description = "테스트 key 에 사용하는 id", in = ParameterIn.PATH)
            @PathVariable
            String id
    ){
        return cacheTestService.findByIdNoCache(id);
    }

    @Operation(summary = "조회 (캐시)", description = "cacheable/id를 이용하여 조회합니다.")
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
