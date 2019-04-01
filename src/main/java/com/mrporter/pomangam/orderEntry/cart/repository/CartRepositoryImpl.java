package com.mrporter.pomangam.orderEntry.cart.repository;

import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.orderEntry.cart.domain.CartDto;
import com.mrporter.pomangam.orderEntry.cartItem.repository.CartItemJpaRepository;
import com.mrporter.pomangam.orderEntry.order.domain.SalesVolumeDto;
import com.mrporter.pomangam.orderEntry.order.repository.OrderRepositoryImpl;
import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTime;
import com.mrporter.pomangam.orderEntry.orderTime.repository.OrderTimeJpaRepository;
import com.mrporter.pomangam.storeEntry.store.domain.Store;
import com.mrporter.pomangam.storeEntry.store.repository.StoreJpaRepository;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class CartRepositoryImpl implements CartRepository {
    @PersistenceContext
    EntityManager em;

    CartItemJpaRepository cartItemJpaRepository;
    OrderRepositoryImpl orderRepository;
    OrderTimeJpaRepository orderTimeJpaRepository;
    StoreJpaRepository storeJpaRepository;

    @Override
    @Modifying
    @Transactional
    public ResponseEntity<?> countCart(Integer customer_idx) {
        String sql = "SELECT * FROM cart_tbl WHERE customer_idx = ?";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, customer_idx);
        JpaResultMapper jpaResultMapper = new JpaResultMapper();

        List<CartDto> carts = jpaResultMapper.list(nativeQuery, CartDto.class);
        if(carts.isEmpty()) {
            return ResponseEntity.ok(0);
        }

        CartDto cart = carts.get(0);
        Date arrivalDate = cart.getArrivalDate();
        if(CustomTime.compareToday(arrivalDate) > 0) {
            sql = "UPDATE cart_tbl SET arrival_date = ? WHERE idx = ?";
            nativeQuery = em.createNativeQuery(sql);
            nativeQuery.setParameter(1, new java.sql.Date(System.currentTimeMillis()));
            nativeQuery.setParameter(2, cart.getIdx());
            nativeQuery.executeUpdate();
        }
        return ResponseEntity.ok(cartItemJpaRepository.countByCartIdx(cart.getIdx()));
    }

    @Override
    public ResponseEntity<?> getArrivalTimeMap(String store_idxes, String arrival_date, Integer delivery_site_idx) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime ldt;
        try {
            ldt = LocalDateTime.parse(arrival_date, formatter);
        } catch (DateTimeParseException dpe) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return getArrivalTimeMap(store_idxes, ldt, delivery_site_idx, ZoneId.of("Asia/Seoul"));
    }

    public ResponseEntity<?> getArrivalTimeMap(String store_idxes, LocalDateTime arrival_date, Integer delivery_site_idx, ZoneId zoneId) {
        Map<Integer, Time> result = new HashMap<>();

        // TZ 설정
        ZonedDateTime arrTimeWithZone = ZonedDateTime.of(
                arrival_date,
                zoneId);

        // protocol : {store_idx}-{quantity},
        // quantity 는 product.type - Main 메뉴에 해당하는 물품 개수만 측정.
        for(String ch : store_idxes.split(",")) {
            Integer store_idx;
            int quantity;
            try {
                store_idx = Integer.parseInt(ch.split("-")[0]);
                quantity = Integer.parseInt(ch.split("-")[1]);
            } catch (NumberFormatException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<SalesVolumeDto> svList = orderRepository.getSalesVolumeByArrivalDateAndStoreIdx(
                    arrival_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    store_idx);
            Store storeInfo = storeJpaRepository.getOne(store_idx);
            int rc; // remaining capacity 현재까지 남은 주문 가능 수량
            int pp = storeInfo.getParallel_production();  // 병렬 생산량
            int mt = storeInfo.getMinimum_time().toLocalTime().getMinute();   // 최소 생산 시간 (분 단위)
            int mp = storeInfo.getMaximum_production();   // 최대 가능 생산량

            for(SalesVolumeDto svDto : svList) {
                Time arrival_time = svDto.getArrival_time();
                int sv = svDto.getSv().intValue();
                if(sv > mp) {
                    // 판매량이 최대 생산량을 초과한 경우
                    continue;
                }

                OrderTime orderTime = orderTimeJpaRepository.getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(store_idx, delivery_site_idx, arrival_time.toString());
                int isPause = orderTime.getStatePause(); // 주문 정지 유무

                if(CustomTime.isToday(arrTimeWithZone)){
                    if (isPause == 1) {
                        continue;
                    }
                }

                ZonedDateTime endTimeWithZone = ZonedDateTime.of(   // (해당 시간대) 주문 마감 시간
                        LocalDateTime.of(arrival_date.toLocalDate(), orderTime.getOrderDeadline().toLocalTime()),
                        zoneId);

                // 남은 시간 계산
                long td = CustomTime.getMinuteByCurrentTimeDifference(endTimeWithZone);

                // 주문 가능 수량 계산
                long temp = (td*pp/mt)-sv;
                rc = temp > mp ? mp : (int)temp;

                if(rc < quantity) {
                    // td가 -1일 경우 (parse error) or 현재까지 남은 주문_가능_수량이 없는 경우
                } else {
                    result.put(store_idx, arrival_time); // list 로 할까??
                }
            }
        }

        return ResponseEntity.ok(result);
    }


/*
    public ResponseEntity<?> getCartWithArrivalTime(Integer cart_Idx) {
        List<CartWithArrivalTimeDto> result = new ArrayList<>();

        // TZ 설정
        ZonedDateTime arrTimeWithZone = ZonedDateTime.of(
                arrival_date,
                zoneId);

        // protocol : {store_idx}-{quantity},
        // quantity 는 product.type - Main 메뉴에 해당하는 물품 개수만 측정.
        for(String ch : store_idxes.split(",")) {
            Integer store_idx;
            int quantity;
            try {
                store_idx = Integer.parseInt(ch.split("-")[0]);
                quantity = Integer.parseInt(ch.split("-")[1]);
            } catch (NumberFormatException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<SalesVolumeDto> svList = orderRepository.getSalesVolumeByArrivalDateAndStoreIdx(
                    arrival_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    store_idx);
            Store storeInfo = storeJpaRepository.getOne(store_idx);
            int rc; // remaining capacity 현재까지 남은 주문 가능 수량
            int pp = storeInfo.getParallel_production();  // 병렬 생산량
            int mt = storeInfo.getMinimum_time().toLocalTime().getMinute();   // 최소 생산 시간 (분 단위)
            int mp = storeInfo.getMaximum_production();   // 최대 가능 생산량

            for(SalesVolumeDto svDto : svList) {
                Time arrival_time = svDto.getArrival_time();
                int sv = svDto.getSv().intValue();
                if(sv > mp) {
                    // 판매량이 최대 생산량을 초과한 경우
                    continue;
                }

                OrderTime orderTime = orderTimeJpaRepository.getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(store_idx, delivery_site_idx, arrival_time.toString());
                int isPause = orderTime.getStatePause(); // 주문 정지 유무

                if(CustomTime.isToday(arrTimeWithZone)){
                    if (isPause == 1) {
                        continue;
                    }
                }

                ZonedDateTime endTimeWithZone = ZonedDateTime.of(   // (해당 시간대) 주문 마감 시간
                        LocalDateTime.of(arrival_date.toLocalDate(), orderTime.getOrderDeadline().toLocalTime()),
                        zoneId);

                // 남은 시간 계산
                long td = CustomTime.getMinuteByCurrentTimeDifference(endTimeWithZone);

                // 주문 가능 수량 계산
                long temp = (td*pp/mt)-sv;
                rc = temp > mp ? mp : (int)temp;

                if(rc < quantity) {
                    // td가 -1일 경우 (parse error) or 현재까지 남은 주문_가능_수량이 없는 경우
                } else {
                    result.put(store_idx, arrival_time); // list 로 할까??
                }
            }
        }

        return ResponseEntity.ok(result);
    }
*/
}
