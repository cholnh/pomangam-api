package com.mrporter.pomangam.storeEntry.store.service;

import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.domain.DetailForDeliverySite;
import com.mrporter.pomangam.deliveryEntry.detailForDeliverySite.repository.DetailForDeliverySiteJpaRepository;
import com.mrporter.pomangam.orderEntry.order.repository.OrderRepositoryImpl;
import com.mrporter.pomangam.orderEntry.orderTime.repository.OrderTimeJpaRepository;
import com.mrporter.pomangam.orderEntry.orderTime.repository.OrderTimeRepositoryImpl;
import com.mrporter.pomangam.storeEntry.scheduleForStore.domain.ScheduleForStore;
import com.mrporter.pomangam.storeEntry.scheduleForStore.repository.ScheduleForStoreJpaRepository;
import com.mrporter.pomangam.storeEntry.store.domain.InquiryResultDto;
import com.mrporter.pomangam.storeEntry.store.domain.Store;
import com.mrporter.pomangam.storeEntry.store.repository.StoreJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    StoreJpaRepository storeJpaRepository;
    OrderTimeRepositoryImpl orderTimeRepository;
    OrderTimeJpaRepository orderTimeJpaRepository;
    OrderRepositoryImpl orderRepositoryImpl;
    DetailForDeliverySiteJpaRepository detailForDeliverySiteJpaRepository;
    ScheduleForStoreJpaRepository scheduleForStoreJpaRepository;

    @Override
    public List<Store> getStoresByIdxes(List<Integer> idxes) {
        return storeJpaRepository.findAllById(idxes);
    }

    public List<InquiryResultDto> getInquiryResult(String arrival_date, Integer detail_for_delivery_site_idx) {
        if(arrival_date == null || detail_for_delivery_site_idx == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt;
        try {
            ldt = LocalDateTime.parse(arrival_date, formatter);
        } catch (DateTimeParseException dpe) {
            return null;
        }
        return getInquiryResult(ldt, detail_for_delivery_site_idx, ZoneId.of("Asia/Seoul"));
    }

    public List<InquiryResultDto> getInquiryResult(LocalDateTime arrival_date, Integer detail_for_delivery_site_idx, ZoneId zoneId) {
        if(arrival_date == null || detail_for_delivery_site_idx == null) {
            return null;
        }

        // 상세 배달지 도착시간 설정
        DetailForDeliverySite detailForDeliverySite = detailForDeliverySiteJpaRepository.getDetailForDeliverySiteByIdx(detail_for_delivery_site_idx);
        if(detailForDeliverySite == null) {
            return null;
        }
        int delivery_site_idx = detailForDeliverySite.getDeliverySiteIdx();
        LocalTime lt = detailForDeliverySite.getOffsetArrivalTime().toLocalTime();
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

            ScheduleForStore schedule = scheduleForStoreJpaRepository.getByStoreIdx(dto.getIdx());
            if(schedule.getState_active().intValue() == 0) {
                // 업체 비활성화 상태
                continue;
            }
            if(schedule.getState_pause().intValue() == 1) {
                // 업체 일시정지 상태
                //dto.setPause_description(schedule.getPause_description());
                //to.setState_pause(Byte.parseByte("1"));
                //dto.setRemaining_capacity(0);
                //dto.setArrival_time(null);
                //result.add(dto);
                continue;
            }
            if(CustomTime.isToday(arrTimeWithZone)){
                // 주문 정지 상태 (해당 시간대 만)
                if (dto.getState_pause().intValue() == 1) {
                    //dto.setRemaining_capacity(0);
                    //dto.setArrival_time(null);
                    //result.add(dto);
                    continue;
                }
            }
            // 3. week_close 계산 처리

            int rc; // remaining capacity 현재까지 남은 주문 가능 수량
            int pp = dto.getParallel_production();  // 병렬 생산량
            int mt = dto.getMinimum_time().toLocalTime().getMinute();   // 최소 생산 시간 (분 단위)
            int mp = dto.getMaximum_production();   // 최대 가능 생산량

            ZonedDateTime endTimeWithZone = ZonedDateTime.of(   // (해당 시간대) 주문 마감 시간
                    LocalDateTime.of(arrival_date.toLocalDate(), dto.getOrder_deadline().toLocalTime()),
                    zoneId);

            // 판매량 계산
            int sv = orderRepositoryImpl.getSalesVolumeByArrivalDateAndTimeAndStoreIdx(
                    arrTimeWithZone.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    arrTimeWithZone.format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                    dto.getIdx());
            if(sv > mp) {
                // 판매량이 최대 생산량을 초과한 경우
                //dto.setRemaining_capacity(0);
                //dto.setArrival_time(null);
                //result.add(dto);
                continue;
            }

            // 남은 시간 계산
            long td = CustomTime.getMinuteByCurrentTimeDifference(endTimeWithZone);

            // 주문 가능 수량 계산
            long temp = td*pp/mt;
            rc = temp > mp ? mp : (int)temp;
            rc -= sv;

            if(rc <= 0) {
                // td가 -1일 경우 (parse error) or 현재까지 남은 주문_가능_수량이 없는 경우
                //dto.setRemaining_capacity(0);
                //dto.setArrival_time(null);
                //result.add(dto);
            } else {
                dto.setRemaining_capacity(rc);
                dto.setArrival_time(zdt);
                result.add(dto);
            }
        }
        return result;
    }
}
