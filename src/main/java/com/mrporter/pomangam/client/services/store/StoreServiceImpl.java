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

    public List<StoreDto> getByIdxDeliverySite(Long didx, Pageable pageable) {
        List<Store> stores = storeJpaRepository.findByIdxDeliverySiteOrderBySequenceAsc(didx, pageable).getContent();
        return StoreDto.fromEntities(stores);
    }


    public StoreDto getByIdx(Long idx) {
        Store entity = storeJpaRepository.findById(idx).get();
        return StoreDto.fromEntity(entity);
    }

    public long count() {
        return storeJpaRepository.count();
    }

    public List<StoreSummaryDto> getSummaries(Long didx, Pageable pageable) {
        List<Store> stores = storeJpaRepository.findByIdxDeliverySiteOrderBySequenceAsc(didx, pageable).getContent();
        List<StoreSummaryDto> dto = StoreSummaryDto.fromEntities(stores);
        return dto;
    }
}
