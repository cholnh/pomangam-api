package com.mrporter.pomangam.common.map.service;

import com.mrporter.pomangam.common.map.domain.CommonMap;

import java.util.List;

public interface CommonMapService {
    List<CommonMap> getValue(String key);
}
