package com.mrporter.pomangam;

import com.mrporter.pomangam._bases.files.service.FileStorageProperties;
import com.mrporter.pomangam.client.test.todo.repository.RepositoryHelper;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.modelmapper.ModelMapper;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@EnableCaching
@EnableAspectJAutoProxy
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
        Locale.setDefault(Locale.KOREA);
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

    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Primary
    public RepositoryHelper provideRepositoryHelper(EntityManager em) {
        return new RepositoryHelper(em);
    }

    @Bean
    @Scope("singleton")
    @Primary
    public AtomicReference<Health> healthAtomicReference() {
        return new AtomicReference<>(Health.up().build());
    }


    @Bean
    @Primary
    public ModelMapper provideModelMapper() {
        return new ModelMapper();
    }
}

