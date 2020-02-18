package com.mrporter.pomangam.client.services.map;

import com.mrporter.pomangam.client.domains.map.CommonMap;

import java.util.List;

public interface CommonMapService {
    List<CommonMap> findAllByKey(String key);
}
