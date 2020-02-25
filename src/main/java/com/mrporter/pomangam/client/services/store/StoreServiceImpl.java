package com.mrporter.pomangam.client.services.store;

import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.StoreDto;
import com.mrporter.pomangam.client.domains.store.StoreSummaryDto;
import com.mrporter.pomangam.client.repositories.order.OrderJpaRepository;
import com.mrporter.pomangam.client.repositories.ordertime.OrderTimeJpaRepository;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
import com.mrporter.pomangam.client.services.store.exception.StoreException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    StoreJpaRepository storeRepo;
    OrderJpaRepository orderRepo;
    OrderTimeJpaRepository orderTimeRepo;

    @Override
    public List<StoreDto> findByIdxDeliverySite(Long dIdx, Pageable pageable) {
        List<Store> stores = storeRepo.findByIdxDeliverySiteAndIsActiveIsTrueOrderBySequenceAsc(dIdx, pageable).getContent();
        return StoreDto.fromEntities(stores);
    }

    @Override
    public StoreDto findByIdx(Long idx) {
        Store entity = storeRepo.findByIdxAndIsActiveIsTrue(idx);
        return StoreDto.fromEntity(entity);
    }

    @Override
    public long count() {
        return storeRepo.countByIsActiveIsTrue();
    }

    @Override
    public List<StoreSummaryDto> findOpeningStores(Long dIdx, Long oIdx, LocalDate oDate, Pageable pageable) {
        List<StoreSummaryDto> summaries = new ArrayList<>();
        LocalTime orderEndTime = _orderEndTime(oIdx);
        if(LocalTime.now().isBefore(orderEndTime)) {
            summaries = _orderableStores(dIdx, oIdx, pageable);
            int dMinute = (int) Duration.between(LocalTime.now(), orderEndTime).toMinutes(); // 주문 마감까지 남은 시간
            for(StoreSummaryDto dto : summaries) {
                int pp = dto.getProductionInfo().getParallelProduction();       // 평균 병렬 생산량
                int mt = dto.getProductionInfo().getMinimumTime();              // 최소 생산 가능 시간
                int max = dto.getProductionInfo().getMaximumProduction();       // 최대 주문 가능 수량
                int avp = pp / mt * dMinute;                                    // 생산 가능 수량
                int aov = orderRepo.accumulatedOrderVolume(dIdx, dto.getIdx(), oIdx, oDate);  // 누적 주문량
                avp = avp >= max ? max : avp;
                dto.setQuantityOrderable(avp - aov);
            }
        }
        return summaries;
    }

    private LocalTime _orderEndTime(Long oIdx) {
        return orderTimeRepo.findById(oIdx)
                .orElseThrow(() -> new StoreException("invalid orderTime."))
                .getOrderEndTime();
    }

    private List<StoreSummaryDto> _orderableStores(Long dIdx, Long oIdx, Pageable pageable) {
        return StoreSummaryDto.fromEntities(orderTimeRepo // (주문시간, 주문장소)에 해당하는 업체들
                .findStoreByIdxOrderTimeAndIdxDeliverySiteAndIsActiveIsTrue(oIdx, dIdx, pageable)
                .getContent());
    }
}
