package com.mrporter.pomangam.client.services._bases;

import com.mrporter.pomangam.client.domains.map.CommonMap;

import java.util.List;

public interface CommonMapService {
    List<CommonMap> findAllByKey(String key);
}
