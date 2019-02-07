package com.mrporter.pomangam.order.repository;

import com.mrporter.pomangam.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface OrderRepository extends JpaRepository<Order, Integer> {
    /*
    @Modifying
    @Query(value =
            "SELECT COUNT(*) " +
            "FROM order_tbl odr, order_item_tbl oit " +
            "WHERE odr.arrival_date_only = :arrival_date " +
            "AND oit.store_idx = :store_idx " +
            "AND odr.arrival_time_only = :arrival_time " +
            "AND oit.order_idx = odr.idx",
            nativeQuery = true)
    int getCountByArrivalDateAndTimeAndStoreIdx(@Param("arrival_date") String arrival_date,
                                                @Param("arrival_time") String arrival_time,
                                                @Param("store_idx") Integer store_idx);
    */
}

