package com.mrporter.pomangam.store.services.store;

import com.mrporter.pomangam.client.domains.store.StoreDto;

public interface StoreService {
    StoreDto findByIdx(Long idx);
    StoreDto patch(StoreDto request);
}
