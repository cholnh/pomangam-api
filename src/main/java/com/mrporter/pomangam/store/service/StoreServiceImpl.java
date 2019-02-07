package com.mrporter.pomangam.store.service;

import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.common.util.time.DateUtils;
import com.mrporter.pomangam.order.repository.OrderCustomRepository;
import com.mrporter.pomangam.order.repository.OrderRepository;
import com.mrporter.pomangam.store.domain.Store;
import com.mrporter.pomangam.store.domain.StoreJoinOrderTimeDto;
import com.mrporter.pomangam.store.repository.OrderTimeRepository;
import com.mrporter.pomangam.store.repository.StoreCustomRepository;
import com.mrporter.pomangam.store.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    OrderRepository orderRepository;
    OrderTimeRepository orderTimeRepository;
    StoreRepository storeRepository;
    StoreCustomRepository storeCustomRepository;
    OrderCustomRepository orderCustomRepository;

    @Override
    public List<Store> getStoresByIdxes(List<Integer> idxes) {
        return storeRepository.findAllById(idxes);
    }

    @Override
    public List<StoreJoinOrderTimeDto> getStoresByArrivalTimeAndDeliverySite(Date arrival_date, Integer delivery_site_idx) {

        // 고객이 고른 시간대에 주문 가능한 업체 리스트
        List<StoreJoinOrderTimeDto> result = new ArrayList<>();
        List<StoreJoinOrderTimeDto> orderTimes = storeCustomRepository.getStoreJoinOrderTimeByArrivalTime(delivery_site_idx, CustomTime.toTimeOnlyByDate(arrival_date));

        int oSize = orderTimes.size();
        for(int i=0; i<oSize; ++i) {
            StoreJoinOrderTimeDto dto = orderTimes.get(i);

            int rc = 0; // remaining capacity 현재까지 남은 주문 가능 수량
            int isPause = dto.getState_pause(); // 주문 정지 유무
            int pp = dto.getParallel_production();  // 병렬 생산량
            int mt = dto.getMinimum_time().toLocalTime().getMinute();   // 최소 생산 시간 (분 단위)
            int mp = dto.getMaximum_production();   // 최대 가능 생산량
            Time endTime = dto.getEnd_time();   // (해당 시간대) 주문 마감 시간
            Integer store_idx = dto.getIdx();

            if(DateUtils.isToday(arrival_date)){
                if (isPause == 1) {
                    continue;
                }
            }

            String arrival_date_only = CustomTime.toDateOnlyByDate(arrival_date);
            String arrival_time_only = CustomTime.toTimeOnlyByDate(arrival_date);

            System.out.println("[dto]");
            System.out.println(dto);

            int sv = orderCustomRepository.getCountByArrivalDateAndTimeAndStoreIdx(arrival_date_only, arrival_time_only, store_idx);
            if(sv > mp) {
                continue;
            }

            int td = CustomTime.getMinuteByTimeDifference(CustomTime.toTimeOnlyByDate(Calendar.getInstance().getTime()), endTime.toString());
            rc = (td*pp/mt)-sv;
            rc = rc > mp ? mp : rc;

            System.out.println("sv : " + sv + " td : " + td + " rc : " + rc);
            System.out.println();

            if(rc <= 0) {
                // td가 -1일 경우 (parse error) or 현재까지 남은 주문_가능_수량이 없는 경우
            } else {
                dto.setRemaining_capacity(rc);
                result.add(dto);
            }
        }
        return result;
    }
}
