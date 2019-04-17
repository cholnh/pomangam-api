package com.mrporter.pomangam.orderEntry.cart.repository;

import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.orderEntry.cart.domain.CartDto;
import com.mrporter.pomangam.orderEntry.cart.domain.CartInStoreQuantityDto;
import com.mrporter.pomangam.orderEntry.cartItem.repository.CartItemJpaRepository;
import com.mrporter.pomangam.orderEntry.order.domain.OrderTimeSalesVolumeDto;
import com.mrporter.pomangam.orderEntry.order.repository.OrderRepositoryImpl;
import com.mrporter.pomangam.orderEntry.orderTime.repository.OrderTimeJpaRepository;
import com.mrporter.pomangam.storeEntry.store.domain.Store;
import com.mrporter.pomangam.storeEntry.store.repository.StoreJpaRepository;
import lombok.AllArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
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
    public int countCart(Integer customer_idx) {
        String sql = "SELECT * FROM cart_tbl WHERE customer_idx = ?";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, customer_idx);
        JpaResultMapper jpaResultMapper = new JpaResultMapper();

        List<CartDto> carts = jpaResultMapper.list(nativeQuery, CartDto.class);
        if(carts.isEmpty()) {
            return 0;
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
        return cartItemJpaRepository.countByCartIdx(cart.getIdx());
    }

    /*
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
*/
    @Override
    public Map<Integer, LocalDateTime> getCartWithArrivalTimeByCustomerIdx(Integer customer_idx) {
        String sql = "SELECT * FROM cart_tbl WHERE customer_idx = ?";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter(1, customer_idx);
        JpaResultMapper jpaResultMapper = new JpaResultMapper();

        CartDto cart = jpaResultMapper.uniqueResult(nativeQuery, CartDto.class);
        if(cart==null) {
            return null;
        }

        return getCartWithArrivalTime(cart.getIdx(), ZoneId.of("Asia/Seoul"));
    }

    @Override
    public Map<Integer, LocalDateTime> getCartWithArrivalTimeByCartIdx(Integer cart_Idx) {

        return getCartWithArrivalTime(cart_Idx, ZoneId.of("Asia/Seoul"));
    }

    // quantity 는 product.type - Main 메뉴에 해당하는 물품 개수만 측정.
    //@Override
    public Map<Integer, LocalDateTime> getCartWithArrivalTime(Integer cart_Idx, ZoneId zoneId) {
        Map<Integer, LocalDateTime> result = new HashMap<>();

        Query nativeQuery1 = em.createNativeQuery(
                "SELECT * " +
                        "FROM cart_tbl " +
                        "where idx = ?"
        );
        nativeQuery1.setParameter(1, cart_Idx);
        CartDto cart = new JpaResultMapper().uniqueResult(nativeQuery1, CartDto.class);

        Query nativeQuery2 = em.createNativeQuery(
                "SELECT store_idx, sum(quantity) as quantity " +
                "FROM item_for_cart_tbl " +
                "where parent_item_idx is null " +
                "and cart_idx = ? " +
                "group by store_idx");
        nativeQuery2.setParameter(1, cart_Idx);
        List<CartInStoreQuantityDto> dtoList = new JpaResultMapper().list(nativeQuery2, CartInStoreQuantityDto.class);
        if(!dtoList.isEmpty()) {
            for(CartInStoreQuantityDto dto : dtoList) {

                // TZ 설정
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime ldt;
                try {
                    ldt = LocalDateTime.parse(cart.getArrivalDate().toString() + " 12:00:00", formatter);
                } catch (DateTimeParseException dpe) {
                    return null;
                }
                ZonedDateTime arrTimeWithZone = ZonedDateTime.of(
                        ldt,
                        zoneId);
                if(CustomTime.isPast(arrTimeWithZone)) {
                    ldt = LocalDateTime.now();
                    arrTimeWithZone = ZonedDateTime.now(zoneId);
                }

                Integer store_idx = dto.getStore_idx();
                int quantity = dto.getQuantity();

                List<OrderTimeSalesVolumeDto> svList = orderRepository.getSalesVolumeByArrivalDateAndStoreIdx(
                        ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        store_idx);
                Store storeInfo = storeJpaRepository.getOne(store_idx);
                int rc; // remaining capacity 현재까지 남은 주문 가능 수량
                int pp = storeInfo.getParallel_production();  // 병렬 생산량
                int mt = storeInfo.getMinimum_time().toLocalTime().getMinute();   // 최소 생산 시간 (분 단위)
                int mp = storeInfo.getMaximum_production();   // 최대 가능 생산량

                //System.out.println("store start : " + storeInfo.getName());

                boolean isTomorrow = true;
                do {
                    if(svList == null || svList.isEmpty()) {
                        break;
                    }
                    for(OrderTimeSalesVolumeDto svDto : svList) {
                        //System.out.println("svDto : " + svDto);
                        //System.out.println("ldt : " + ldt);

                        LocalDateTime arrival_time = LocalDateTime.of(ldt.toLocalDate(), svDto.getArrival_time().toLocalTime());

                        int sv = svDto.getSv()==null?0:svDto.getSv().intValue();
                        if(sv > mp) {
                            // 판매량이 최대 생산량을 초과한 경우
                            continue;
                        }

                        //OrderTime orderTime = orderTimeJpaRepository.getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(store_idx, cart.getDetailSiteIdx(), arrival_time.toString());
                        //int isPause = orderTime.getStatePause(); // 주문 정지 유무
                        int isPause = svDto.getState_pause();

                        if(CustomTime.isToday(arrTimeWithZone)){
                            if (isPause == 1) {
                                continue;
                            }
                        }

                        ZonedDateTime endTimeWithZone = ZonedDateTime.of(   // (해당 시간대) 주문 마감 시간
                                //LocalDateTime.of(ldt.toLocalDate(), orderTime.getOrderDeadline().toLocalTime()),
                                LocalDateTime.of(ldt.toLocalDate(), svDto.getOrder_deadline().toLocalTime()),
                                zoneId);

                        // 남은 시간 계산
                        long td = CustomTime.getMinuteByCurrentTimeDifference(endTimeWithZone);

                        // 주문 가능 수량 계산
                        long temp = td*pp/mt;
                        rc = temp > mp ? mp : (int)temp;
                        rc -= sv;

                        //System.out.println("temp : "+temp + " rc : " + rc + " quantity : " + quantity);

                        if(rc < quantity) {
                            // td가 -1일 경우 (parse error) or 현재까지 남은 주문_가능_수량이 없는 경우
                        } else {
                            isTomorrow = false; // 금일 주문이 가능한 경우 이므로 다음날로 넘어가진 않음 (false)
                            result.put(store_idx, arrival_time); // list 로 할까??
                            break;
                        }
                    }
                    if(isTomorrow) {
                        ldt = ldt.plus(1, ChronoUnit.DAYS);
                        //System.out.println("다음날");
                    }
                } while(isTomorrow);
            }
        }
        return result;
    }
    public static void main(String...args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = null;
        try {
            ldt = LocalDateTime.parse("2019-02-21 12:00:00", formatter);
        } catch (DateTimeParseException dpe) {
            System.out.println(dpe);
        }
        System.out.println(ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

    }
}