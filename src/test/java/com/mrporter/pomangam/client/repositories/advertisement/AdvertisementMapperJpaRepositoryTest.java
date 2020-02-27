package com.mrporter.pomangam.client.repositories.advertisement;

import com.mrporter.pomangam.client.domains.advertisement.Advertisement;
import com.mrporter.pomangam.client.domains.advertisement.AdvertisementMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(SpringRunner.class)
public class AdvertisementMapperJpaRepositoryTest {

    @Autowired
    AdvertisementMapperJpaRepository advertisementMapperJpaRepository;

    @Test
    public void 광고_MAPPER_N1_문제_테스트() {
        Page<AdvertisementMapper> page = advertisementMapperJpaRepository.findAdvertisementByDeliverySite_IdxAndIsActiveIsTrue(1L, PageRequest.of(0, 10));
        List<AdvertisementMapper> advertisementMappers =  page.getContent();
        for(AdvertisementMapper advertisementMapper : advertisementMappers) {
            System.out.println(advertisementMapper.getAdvertisement());
        }

    }
}
