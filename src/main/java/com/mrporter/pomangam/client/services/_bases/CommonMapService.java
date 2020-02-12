package com.mrporter.pomangam.client.services._bases;

import com.mrporter.pomangam.client.domains._bases.CommonMap;

import java.util.List;

public interface CommonMapService {
    List<CommonMap> getValue(String key);
}
