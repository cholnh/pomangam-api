package com.mrporter.pomangam.storeEntry.store.service;

import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain.DetailForDeliverySite;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.repository.DetailForDeliverySiteJpaRepository;
import com.mrporter.pomangam.orderEntry.order.repository.OrderRepositoryImpl;
import com.mrporter.pomangam.orderEntry.orderTime.repository.OrderTimeRepositoryImpl;
import com.mrporter.pomangam.storeEntry.store.repository.StoreJpaRepository;
import com.mrporter.pomangam.storeEntry.store.domain.InquiryResultDto;
import com.mrporter.pomangam.storeEntry.store.domain.Store;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    StoreJpaRepository storeJpaRepository;
    OrderTimeRepositoryImpl orderTimeRepository;
    OrderRepositoryImpl orderRepositoryImpl;
    DetailForDeliverySiteJpaRepository detailForDeliverySiteJpaRepository;

    @Override
    public List<Store> getStoresByIdxes(List<Integer> idxes) {
        return storeJpaRepository.findAllById(idxes);
    }

    @Override
    public List<InquiryResultDto> getInquiryResult(LocalDateTime arrival_date, Integer detail_for_delivery_site_idx) {
        return getInquiryResult(arrival_date, detail_for_delivery_site_idx, ZoneId.of("Asia/Seoul"));
    }

    @Override
    public List<InquiryResultDto> getInquiryResult(LocalDateTime arrival_date, Integer detail_for_delivery_site_idx, ZoneId zoneId) {

        // 상세 배달지 도착시간 설정
        DetailForDeliverySite detailForDeliverySite = detailForDeliverySiteJpaRepository.getDetailForDeliverySiteByIdx(detail_for_delivery_site_idx);
        int delivery_site_idx = detailForDeliverySite.getDelivery_site_idx();
        LocalTime lt = detailForDeliverySite.getOffset_arrival_time().toLocalTime();
        ZonedDateTime zdt = ZonedDateTime.of(arrival_date, zoneId)
                .plusHours(lt.getHour())
                .plusMinutes(lt.getMinute());

        // TZ 설정
        ZonedDateTime arrTimeWithZone = ZonedDateTime.of(
                arrival_date,
                zoneId);

        // 고객이 고른 시간대에 주문 가능한 업체 리스트
        List<InquiryResultDto> result = new ArrayList<>();
        List<InquiryResultDto> orderTimes = orderTimeRepository.getInquiryResult(
                delivery_site_idx,
                arrTimeWithZone.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        int oSize = orderTimes.size();
        for(int i=0; i<oSize; ++i) {
            InquiryResultDto dto = orderTimes.get(i);

            // schedule_for_store_tbl - store_tbl join 하여
            // 1. arrival_date_time - close_time 비교(x)
            // 2. state_active, state_pause, pause_description -> dto에 추가해서 보내자 (schedule_for_store_tbl 정보 모두 고려 해보셈)
            // 3. week_close 계산 처리

            int rc; // remaining capacity 현재까지 남은 주문 가능 수량
            int isPause = dto.getState_pause(); // 주문 정지 유무
            int pp = dto.getParallel_production();  // 병렬 생산량
            int mt = dto.getMinimum_time().toLocalTime().getMinute();   // 최소 생산 시간 (분 단위)
            int mp = dto.getMaximum_production();   // 최대 가능 생산량
            Integer store_idx = dto.getIdx();

            ZonedDateTime endTimeWithZone = ZonedDateTime.of(   // (해당 시간대) 주문 마감 시간
                    LocalDateTime.of(arrival_date.toLocalDate(), dto.getEnd_time().toLocalTime()),
                    zoneId);

            //System.out.println("isToday : "+CustomTime.isToday(arrTimeWithZone)); 과거:1 현재:0 미래:-1
            if(CustomTime.isToday(arrTimeWithZone) == 0){
                if (isPause == 1) {
                    continue;
                }
            }

            System.out.println("[dto]");
            System.out.println(dto);

            // 판매량 계산
            int sv = orderRepositoryImpl.getSalesVolumeByArrivalDateAndTimeAndStoreIdx(
                    arrTimeWithZone.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    arrTimeWithZone.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                    store_idx);
            if(sv > mp) {
                // 판매량이 최대 생산량을 초과한 경우
                continue;
            }

            // 남은 시간 계산
            long td = CustomTime.getMinuteByCurrentTimeDifference(endTimeWithZone);

            // 주문 가능 수량 계산
            long temp = (td*pp/mt)-sv;
            rc = temp > mp ? mp : (int)temp;

            System.out.println("sv : " + sv + " td : " + td + " rc : " + rc);
            System.out.println();

            if(rc <= 0) {
                // td가 -1일 경우 (parse error) or 현재까지 남은 주문_가능_수량이 없는 경우
            } else {
                dto.setRemaining_capacity(rc);
                dto.setArrival_time(zdt);
                result.add(dto);
            }
        }
        return result;
    }
}
