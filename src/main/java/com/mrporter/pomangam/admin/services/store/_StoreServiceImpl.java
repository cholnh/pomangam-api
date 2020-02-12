package com.mrporter.pomangam.admin.services.store;

import com.mrporter.pomangam.admin.domains.store._StoreDto;
import com.mrporter.pomangam.admin.repositories.store._StoreJpaRepository;
import com.mrporter.pomangam.client.domains.store.StoreDto;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
import com.mrporter.pomangam.client.services.store.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class _StoreServiceImpl implements _StoreService {

    _StoreJpaRepository storeJpaRepository;

    public List<_StoreDto> find() {
        return _StoreDto.fromEntities(storeJpaRepository.findAll());
    }
}
