package com.mrporter.pomangam.client.services.store;

import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.StoreDto;
import com.mrporter.pomangam.client.domains.store.StoreSummaryDto;
import com.mrporter.pomangam.client.repositories.ordertime.OrderTimeJpaRepository;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    StoreJpaRepository storeJpaRepository;
    OrderTimeJpaRepository orderTimeJpaRepository;

    @Override
    public List<StoreDto> findByIdxDeliverySite(Long dIdx, Pageable pageable) {
        List<Store> stores = storeJpaRepository.findByIdxDeliverySiteAndIsActiveIsTrueOrderBySequenceAsc(dIdx, pageable).getContent();
        return StoreDto.fromEntities(stores);
    }

    @Override
    public StoreDto findByIdx(Long idx) {
        Store entity = storeJpaRepository.findByIdxAndIsActiveIsTrue(idx);
        return StoreDto.fromEntity(entity);
    }

    @Override
    public long count() {
        return storeJpaRepository.countByIsActiveIsTrue();
    }

    @Override
    public List<StoreSummaryDto> findOpeningStores(Long dIdx, Long oIdx, Pageable pageable) {
        List<Store> stores // 범위내 업체들 리스트
                = orderTimeJpaRepository.findStoreByIdxOrderTimeAndIdxDeliverySiteAndIsActiveIsTrue(oIdx, dIdx, pageable).getContent();
        List<StoreSummaryDto> dtos = StoreSummaryDto.fromEntities(stores);

        for(StoreSummaryDto dto : dtos) {
            // Todo: orderable 확인

        }

        return dtos;
    }
}
