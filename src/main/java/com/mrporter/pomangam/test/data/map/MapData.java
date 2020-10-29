package com.mrporter.pomangam.test.data.map;

import com.mrporter.pomangam.client.domains.map.CommonMap;
import com.mrporter.pomangam.client.repositories.map.CommonMapJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MapData {

    @Autowired
    CommonMapJpaRepository commonMapJpaRepository;

    @Transactional
    public void of(String key, String value) {
        CommonMap commonMap = CommonMap.builder()
                .key(key)
                .value(value)
                .build();
        commonMapJpaRepository.save(commonMap);
    }
}