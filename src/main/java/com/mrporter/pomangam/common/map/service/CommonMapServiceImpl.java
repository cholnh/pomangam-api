package com.mrporter.pomangam.common.map.service;

import com.mrporter.pomangam.common.map.domain.CommonMap;
import com.mrporter.pomangam.common.map.repository.CommonMapJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommonMapServiceImpl implements CommonMapService {

    CommonMapJpaRepository commonMapJpaRepository;

    @Override
    public List<CommonMap> getValue(String key) {
        return commonMapJpaRepository.findByKey(key);
    }
}
