package com.mrporter.pomangam.client.services.store;

import com.mrporter.pomangam.client.domains.store.StoreDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService {
    List<StoreDto> get(Pageable pageable);
}
