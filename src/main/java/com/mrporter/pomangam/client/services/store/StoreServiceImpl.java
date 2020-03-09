package com.mrporter.pomangam.client.services.store;

import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.StoreDto;
import com.mrporter.pomangam.client.domains.store.StoreQuantityOrderableDto;
import com.mrporter.pomangam.client.domains.store.StoreSummaryDto;
import com.mrporter.pomangam.client.domains.store.info.ProductionInfo;
import com.mrporter.pomangam.client.repositories.order.OrderJpaRepository;
import com.mrporter.pomangam.client.repositories.ordertime.OrderTimeJpaRepository;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
import com.mrporter.pomangam.client.repositories.store.like.StoreLikeJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import com.mrporter.pomangam.client.services.store.exception.StoreException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    StoreJpaRepository storeRepo;
    OrderJpaRepository orderRepo;
    OrderTimeJpaRepository orderTimeRepo;
    StoreLikeJpaRepository storeLikeRepo;
    UserJpaRepository userRepo;

    @Override
    public List<StoreDto> findByIdxDeliverySite(Long dIdx, Pageable pageable) {
        List<Store> stores = storeRepo.findByIdxDeliverySiteAndIsActiveIsTrueOrderBySequenceAsc(dIdx, pageable).getContent();
        return StoreDto.fromEntities(stores);
    }

    @Override
    public StoreDto findByIdx(Long idx, String phoneNumber) {
        Store entity = storeRepo.findByIdxAndIsActiveIsTrue(idx);
        StoreDto dto = StoreDto.fromEntity(entity);
        boolean isLike = false;
        if(phoneNumber != null) {
            Long uIdx = userRepo.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
            isLike = storeLikeRepo.existsByIdxUserAndIdxStore(uIdx, idx);
        }
        dto.setIsLike(isLike);
        return dto;
    }

    @Override
    public long count() {
        return storeRepo.countByIsActiveIsTrue();
    }

    @Override
    public List<StoreSummaryDto> findOpeningStores(Long dIdx, Long oIdx, LocalDate oDate, Pageable pageable) {
        List<StoreSummaryDto> summaries = new ArrayList<>();

        LocalDateTime orderEndTime = LocalDateTime.of(oDate, _orderEndTime(oIdx));
        LocalDateTime now = LocalDateTime.now();

        if(now.isBefore(orderEndTime)) {
            int dMinute = (int) Duration.between(now, orderEndTime).toMinutes();  // 주문 마감까지 남은 시간
            for(Store store : _orderableStores(dIdx, oIdx, pageable)) {
                StoreSummaryDto dto = StoreSummaryDto.fromEntity(store);
                dto.setQuantityOrderable(qo(store.getProductionInfo(), dMinute, aov(dIdx, store.getIdx(), oIdx, oDate)));
                summaries.add(dto);
            }
        }
        return summaries;
    }

    @Override
    public List<StoreQuantityOrderableDto> findQuantityOrderableByIdxes(Long dIdx, Long oIdx, LocalDate oDate, List<Long> sIdxes) {
        List<StoreQuantityOrderableDto> quantities = new ArrayList<>();

        int dMinute = (int) Duration.between(LocalDateTime.now(), LocalDateTime.of(oDate, _orderEndTime(oIdx))).toMinutes(); // 주문 마감까지 남은 시간
        for(Store store : storeRepo.findAllById(sIdxes)) {
            quantities.add(StoreQuantityOrderableDto.builder()
                    .idx(store.getIdx())
                    .quantityOrderable(qo(store.getProductionInfo(), dMinute, aov(dIdx, store.getIdx(), oIdx, oDate)))
                    .build());
        }
        return quantities;
    }

    @Override
    public long countOpeningStores(Long dIdx, Long oIdx, LocalDate oDate) {
        LocalDateTime orderEndTime = LocalDateTime.of(oDate, _orderEndTime(oIdx));
        LocalDateTime now = LocalDateTime.now();

        if(now.isBefore(orderEndTime)) {
            return storeRepo.countByIdxOrderTimeAndIdxDeliverySiteAndIsActiveIsTrue(oIdx, dIdx);
        } else {
            return 0L;
        }
    }

    /**
     * 주문 가능 수량
     * quantityOrderable
     */
    private int qo(ProductionInfo info, int dMinute, int aov) {
        int pp = info.getParallelProduction();       // 평균 병렬 생산량
        int mt = info.getMinimumTime();              // 최소 생산 가능 시간
        int max = info.getMaximumProduction();       // 최대 주문 가능 수량
        int avp = pp / mt * dMinute;                 // 생산 가능 수량
        avp = avp >= max ? max : avp;
        return avp - aov < 0 ? 0 : avp - aov;
    }

    /**
     * 누적 주문량
     * available order volume
     */
    private int aov(Long dIdx, Long sIdx, Long oIdx, LocalDate oDate) {
        return orderRepo.accumulatedOrderVolume(dIdx, sIdx, oIdx, oDate);
    }

    private LocalTime _orderEndTime(Long oIdx) {
        return orderTimeRepo.findById(oIdx)
                .orElseThrow(() -> new StoreException("invalid orderTime."))
                .getOrderEndTime();
    }

    private List<Store> _orderableStores(Long dIdx, Long oIdx, Pageable pageable) {
        return storeRepo // (주문시간, 주문장소)에 해당하는 업체들
            .findStoreByIdxOrderTimeAndIdxDeliverySiteAndIsActiveIsTrue(oIdx, dIdx, pageable)
            .getContent();
    }
}
