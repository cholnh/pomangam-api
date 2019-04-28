package com.mrporter.pomangam.orderEntry.orderTime.service;

import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.orderEntry.order.domain.OrderTimeSalesVolumeDto;
import com.mrporter.pomangam.orderEntry.order.repository.OrderRepositoryImpl;
import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTime;
import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTimeDto;
import com.mrporter.pomangam.orderEntry.orderTime.repository.OrderTimeJpaRepository;
import com.mrporter.pomangam.orderEntry.orderTime.repository.OrderTimeRepositoryImpl;
import com.mrporter.pomangam.storeEntry.store.domain.Store;
import com.mrporter.pomangam.storeEntry.store.repository.StoreJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderTimeServiceImpl implements OrderTimeService {

    OrderRepositoryImpl orderRepository;
    StoreJpaRepository storeJpaRepository;
    OrderTimeRepositoryImpl orderTimeRepository;
    OrderTimeJpaRepository orderTimeJpaRepository;

    @Override
    public OrderTime getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(Integer store_idx,
                                                                      Integer delivery_site_idx,
                                                                      String arrival_time) {
        return orderTimeJpaRepository.getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(store_idx,delivery_site_idx,arrival_time);
    }

    @Override
    public List<OrderTimeDto> getOrderTimesByDeliverySiteIdx(Integer delivery_site_idx) {
        return orderTimeRepository.getOrderTimesByDeliverySiteIdx(delivery_site_idx);
    }

    @Override
    public List<OrderTimeDto> getOrderTimesByDeliverySiteIdxAndArrivalTime(Integer delivery_site_idx) {
        return orderTimeRepository.getOrderTimesByDeliverySiteIdxAndArrivalTime(delivery_site_idx);
    }

    @Override
    public LocalDateTime getMinimumArrivalTime(Integer store_idx, int quantity) {
        LocalDateTime result = LocalDateTime.now();

        List<OrderTimeSalesVolumeDto> svList = orderRepository.getSalesVolumeByArrivalDateAndStoreIdx(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                store_idx);
        Store storeInfo = storeJpaRepository.getOne(store_idx);
        int rc; // remaining capacity 현재까지 남은 주문 가능 수량
        int pp = storeInfo.getParallel_production();  // 병렬 생산량
        int mt = storeInfo.getMinimum_time().toLocalTime().getMinute();   // 최소 생산 시간 (분 단위)
        int mp = storeInfo.getMaximum_production();   // 최대 가능 생산량

        int roopCnt = 0;
        boolean isTomorrow = true;
        do {
            if(++roopCnt > 20) {
                // 무한 루프 방지
                return null;
            }

            if(svList == null || svList.isEmpty()) {
                return null;
            }
            for(OrderTimeSalesVolumeDto svDto : svList) {

                LocalDateTime arrival_time = LocalDateTime.of(result.toLocalDate(), svDto.getArrival_time().toLocalTime());

                int sv = svDto.getSv()==null?0:svDto.getSv().intValue();
                if(sv > mp) {
                    // 판매량이 최대 생산량을 초과한 경우
                    continue;
                }

                if(CustomTime.isToday(ZonedDateTime.of(arrival_time, ZoneId.systemDefault()))) {
                    if (svDto.getState_pause().intValue()== 1) {
                        // (해당 시간대) 주문 정지
                        continue;
                    }
                }

                // (해당 시간대) 주문 마감 시간
                ZonedDateTime endTimeWithZone = ZonedDateTime.of(
                        LocalDateTime.of(result.toLocalDate(), svDto.getOrder_deadline().toLocalTime()),
                        ZoneId.systemDefault());

                // 남은 시간 계산
                long td = CustomTime.getMinuteByCurrentTimeDifference(endTimeWithZone);

                // 주문 가능 수량 계산
                long temp = td*pp/mt;
                rc = temp > mp ? mp : (int)temp;
                rc -= sv;

                if(rc < quantity) {
                    // td가 -1일 경우 (parse error) or 현재까지 남은 주문_가능_수량이 없는 경우
                    if(rc > 0) {
                        // quantity 에 문제가 생긴 경우
                        return null;
                    }
                } else {
                    return arrival_time;
                }
            }
            if(isTomorrow) {
                result = result.plus(1, ChronoUnit.DAYS);
            }
        } while(isTomorrow);

        return result;
    }

}
