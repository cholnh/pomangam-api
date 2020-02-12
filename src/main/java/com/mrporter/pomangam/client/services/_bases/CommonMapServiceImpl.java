package com.mrporter.pomangam.client.services._bases;

import com.mrporter.pomangam.client.domains._bases.CommonMap;
import com.mrporter.pomangam.client.repositories._bases.CommonMapJpaRepository;
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