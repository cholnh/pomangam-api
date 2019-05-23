package com.mrporter.pomangam.test.mapObserver.service;

import com.mrporter.pomangam.test.mapObserver.domain.Map;

public interface MapObserverService {
    Map getBy(Integer employeeIdx);
    Map post(Map map);
    Map patch(Integer employeeIdx, Map map);
}
