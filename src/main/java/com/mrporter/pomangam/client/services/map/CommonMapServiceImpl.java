package com.mrporter.pomangam.client.services.map;

import com.mrporter.pomangam.client.domains.map.CommonMap;
import com.mrporter.pomangam.client.repositories.map.CommonMapJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommonMapServiceImpl implements CommonMapService {

    CommonMapJpaRepository commonMapJpaRepository;

    @Override
    public List<CommonMap> findAllByKey(String key) {
        return commonMapJpaRepository.findByKey(key);
    }
}
