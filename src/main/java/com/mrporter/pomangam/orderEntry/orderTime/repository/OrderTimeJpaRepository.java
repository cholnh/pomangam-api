package com.mrporter.pomangam.orderEntry.orderTime.repository;

import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface OrderTimeJpaRepository extends JpaRepository<OrderTime, Integer> {
    OrderTime getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(
            @Param("storeIdx") Integer store_idx,
            @Param("deliverySiteIdx") Integer delivery_site_idx,
            @Param("arrivalTime") String arrival_time
    );
}

