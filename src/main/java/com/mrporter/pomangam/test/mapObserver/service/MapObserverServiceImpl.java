package com.mrporter.pomangam.test.mapObserver.service;

import com.mrporter.pomangam.test.mapObserver.domain.Map;
import com.mrporter.pomangam.test.mapObserver.repository.MapObserverJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MapObserverServiceImpl implements MapObserverService {

    MapObserverJpaRepository mapObserverJpaRepository;

    @Override
    @Cacheable(value = "mapObserver", key="#employeeIdx")
    public Map getBy(Integer employeeIdx) {
        Optional<Map> optional = mapObserverJpaRepository.findById(employeeIdx);
        if(optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

    @Override
    public Map post(Map map) {
        return mapObserverJpaRepository.save(map);
    }

    @Override
    @CachePut(value = "mapObserver", key="#employeeIdx")
    public Map patch(Integer employeeIdx, Map map) {
        Optional<Map> optional = mapObserverJpaRepository.findById(employeeIdx);
        if(!optional.isPresent()) {
            return null;
        }
        final Map fetchedMap = optional.get();

        if (map.getEmployee_idx() != null) {
            fetchedMap.setEmployee_idx(map.getEmployee_idx());
        }
        if (map.getLatitude() != null) {
            fetchedMap.setLatitude(map.getLatitude());
        }
        if (map.getLongitude() != null) {
            fetchedMap.setLongitude(map.getLongitude());
        }
        if (map.getDirection() != null) {
            fetchedMap.setDirection(map.getDirection());
        }
        if (map.getState() != null) {
            fetchedMap.setState(map.getState());
        }
        return mapObserverJpaRepository.save(fetchedMap);
    }
}
