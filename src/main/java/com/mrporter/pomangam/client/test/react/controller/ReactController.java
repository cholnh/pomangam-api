package com.mrporter.pomangam.client.test.react.controller;

import com.mrporter.pomangam._bases.aspects.annotation.LogExecutionTime;
import com.mrporter.pomangam.client.test.react.service.ReactService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests/react")
@Slf4j
@AllArgsConstructor
public class ReactController {

    private ReactService reactService;

    /**
     * spring5, spring boot2.x 에 추가된 WebFlux 를 도입하려 했으나,
     * jdbc(jpa)는 reactRepository 가 지원되지 않고,
     * view 핸들링 같은 경우 db로 부터 자원을 fetch 하여 비지니스 로직을 처리하는 과정 자체에서
     * block 되므로, WebFlux 도입을 하지 않았다.
     *
     * Thread 자원을 반환하는 정도라면 도입해볼 만 하다.
     * 예시)
     *
     *   Mono blockingWrapper = Mono.fromCallable(() -> {
     *       return // make a remote synchronous call //
     *   }).subscribeOn(Schedulers.elastic());
     *
     * Mono.fromCallable를 사용하여 blocking call 부분의 실행을 미루고,
     * 이를 Schedulers.elastic()를 사용하여 blocking 자원들을 기다리는 별도의 쓰레드를 생성하여 실행
     *
     * (하지만 결국 Thread 자원이 늘어나고, 컨텍스트 스위칭이 발생하게 되어 비효율적이지 않을까 싶다.)
     */

    @LogExecutionTime
    @GetMapping
    public ResponseEntity<?> test1(){
        log.info("controller test1 in");
        ResponseEntity res = new ResponseEntity(reactService.test1(), HttpStatus.OK);
        log.info("controller test1 out");
        return res;
    }
}
