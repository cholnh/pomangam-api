package com.mrporter.pomangam.client.services.store;

import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.StoreDto;
import com.mrporter.pomangam.client.domains.store.StoreSummaryDto;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    StoreJpaRepository storeJpaRepository;

    @Override
    public List<StoreDto> findByIdxDeliverySite(Long dIdx, Pageable pageable) {
        List<Store> stores = storeJpaRepository.findByIdxDeliverySiteOrderBySequenceAsc(dIdx, pageable).getContent();
        return StoreDto.fromEntities(stores);
    }

    @Override
    public StoreDto findByIdx(Long idx) {
        Store entity = storeJpaRepository.findById(idx).get();
        return StoreDto.fromEntity(entity);
    }

    @Override
    public long count() {
        return storeJpaRepository.count();
    }

    @Override
    public List<StoreSummaryDto> findSummaries(Long dIdx, Pageable pageable) {
        List<Store> stores = storeJpaRepository.findByIdxDeliverySiteOrderBySequenceAsc(dIdx, pageable).getContent();
        List<StoreSummaryDto> dto = StoreSummaryDto.fromEntities(stores);
        return dto;
    }
}
