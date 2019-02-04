package com.mrporter.pomangam.store.service;

import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.order.repository.OrderRepository;
import com.mrporter.pomangam.store.domain.Store;
import com.mrporter.pomangam.store.domain.StoreJoinOrderTimeDto;
import com.mrporter.pomangam.store.repository.OrderTimeRepository;
import com.mrporter.pomangam.store.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    OrderRepository orderRepository;
    OrderTimeRepository orderTimeRepository;
    StoreRepository storeRepository;

    @Override
    public List<Store> getStoresByIdxes(List<Long> idxes) {
        return storeRepository.findAllById(idxes);
    }

    @Override
    public List<StoreJoinOrderTimeDto> getStoresByArrivalTimeAndDetailDeliverySite(Date arrival_date, Long delivery_site_idx, Long detail_delivery_site_idx) {
        List<StoreJoinOrderTimeDto> orderTimes = orderTimeRepository.getStoreJoinOrderTimeByArrivalTime(delivery_site_idx, CustomTime.toTimeByDate(arrival_date));

        int oSize = orderTimes.size();
        for(int i=0; i<oSize; ++i) {
            StoreJoinOrderTimeDto dto = orderTimes.get(i);

            int rc = 0;
            int isPause = dto.getState_pause();
            int pp = dto.getParallel_production();
            Time mt = dto.getMinimum_time();
            Time endTime = dto.getEnd_time();

            if(오늘 : arrival_date.date == today.date){
                if (isPause == 1) {
                    orderTimes.remove(dto);
                    continue;
                }
            }

            int sv = orderRepository.getCountByArrivalDateAndTimeAndStoreIdx(arrival_date.date, arrival_date.time, Long store_idx);
            rc = ((endTime.time - arrival_date.time(단위:분))*pp/mt)-sv)

            if(rc <= 0) {
                orderTimes.remove(dto);
            }
        }

        return orderTimes;
    }

    /*
    @Override
    public List<OrderTime> getOrderTimeByArrivalTime(Long delivery_site_idx, String arrival_time) {
        return orderTimeRepository.getOrderTimeByArrivalTime(delivery_site_idx, arrival_time);
    }
    */
}
