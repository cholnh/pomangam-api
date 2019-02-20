package com.mrporter.pomangam.orderEntry.orderTime.repository;

import com.mrporter.pomangam.orderEntry.orderTime.domain.OrderTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface OrderTimeJpaRepository extends JpaRepository<OrderTime, Integer> {
    OrderTime getByStoreIdxAndDeliverySiteIdxAndAndArrivalTime(
            @Param("sidx") Integer store_idx,
            @Param("didx") Integer delivery_site_idx,
            @Param("time") String arrival_time
    );
}

