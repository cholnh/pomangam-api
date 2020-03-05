package com.mrporter.pomangam.client.test.react.service;

import com.mrporter.pomangam.client.test.react.repository.ReactRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ReactService {
    private ReactRepository reactRepository;

    public String test1() {
        log.info("ReactService test1 in");
        String res1 = reactRepository.test1();
        String res2 = reactRepository.test2();
        log.info("ReactService test1 out");
        return res1 + " " + res2;
    }
}
