package com.mrporter.pomangam;

import com.mrporter.pomangam.common.map.repository.CommonMapRepository;
import com.mrporter.pomangam.common.map.domain.CommonMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PomangamApplicationTests {

    @Autowired
    CommonMapRepository commonMapRepository;

    @Test
    public void contextLoads() {
    }

}

