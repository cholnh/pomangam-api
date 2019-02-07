package com.mrporter.pomangam.store.repository;

import com.mrporter.pomangam.store.domain.OrderTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface OrderTimeRepository extends JpaRepository<OrderTime, Long> {
    /*
    @Modifying
    @Query(value =
            "SELECT str.*, odr.end_time, odr.state_pause " +
            "FROM ordertime_for_store_tbl odr, store_tbl str " +
            "WHERE odr.delivery_site_idx = :delivery_site_idx " +
            "AND odr.arrival_time = :arrival_time " +
            "AND odr.arrival_tomorrow = 0 " +
            "AND odr.store_idx = str.idx",
            nativeQuery = true)

    List<StoreJoinOrderTimeDto> getStoreJoinOrderTimeByArrivalTime(@Param("delivery_site_idx") Long delivery_site_idx,
                                                          @Param("arrival_time") String arrival_time);
    */
    OrderTime getOrderTimeByIdx(Long idx);
}

