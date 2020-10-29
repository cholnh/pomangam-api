package com.mrporter.pomangam.client.services.map;

import com.mrporter.pomangam.client.domains.map.CommonMap;
import com.mrporter.pomangam.client.repositories.map.CommonMapJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommonMapServiceImpl implements CommonMapService {

    CommonMapJpaRepository commonMapRepo;

    @Override
    public List<CommonMap> findAllByKey(String key) {
        return commonMapRepo.findByKeyAndIsActiveIsTrue(key);
    }

    @Override
    public String findValueByKey(String key) {
        List<CommonMap> maps = commonMapRepo.findByKeyAndIsActiveIsTrue(key);
        if(maps == null || maps.isEmpty()) {
            return null;
        } else {
            return maps.get(0).getValue();
        }
    }
}
