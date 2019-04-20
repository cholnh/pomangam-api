package com.mrporter.pomangam;

import com.mrporter.pomangam.common.file.config.FileStorageProperties;
import org.apache.commons.dbutils.QueryRunner;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.TimeZone;

@EnableConfigurationProperties({FileStorageProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class PomangamApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PomangamApplication.class, args);
    }

    @PostConstruct
    /* 초기화 */
    public void initApplication() {
        /* TZ */
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    @Override
    /* 구동 시점에 실행되는 코드 */
    public void run(String... args) throws Exception {
    }

    @Bean
    @Primary
    QueryRunner queryRunner (DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    @Bean
    @Primary
    JpaResultMapper jpaResultMapper () {
        return new JpaResultMapper();
    }
}

