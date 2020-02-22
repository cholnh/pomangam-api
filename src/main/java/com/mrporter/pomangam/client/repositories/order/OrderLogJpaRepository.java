package com.mrporter.pomangam.client.repositories.order;

import com.mrporter.pomangam.client.domains.order.log.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface OrderLogJpaRepository extends JpaRepository<OrderLog, Long>, OrderLogCustomRepository {

}

interface OrderLogCustomRepository {

}

@Transactional(readOnly = true)
class OrderLogCustomRepositoryImpl extends QuerydslRepositorySupport implements OrderLogCustomRepository {

    public OrderLogCustomRepositoryImpl() {
        super(OrderLog.class);
    }


}