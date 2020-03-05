package com.mrporter.pomangam.client.test.react.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ReactRepository {
    public String test1() {
        log.info("ReactRepository test1 in");
        try {
            log.info("big query1 start");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("big query1 end");
        }
        log.info("ReactRepository test1 out");
        return "good1";
    }

    public String test2() {
        log.info("ReactRepository test2 in");
        try {
            log.info("big query2 start");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("big query2 end");
        }
        log.info("ReactRepository test2 out");
        return "good2";
    }
}
