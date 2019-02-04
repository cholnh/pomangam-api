package com.mrporter.pomangam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.TimeZone;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class PomangamApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PomangamApplication.class, args);
    }



    @Override
    /* 구동 시점에 실행되는 코드 */
    public void run(String... args) throws Exception {

        /* TZ */
        TimeZone.setDefault(TimeZone.getTimeZone("KST"));

    }
}

