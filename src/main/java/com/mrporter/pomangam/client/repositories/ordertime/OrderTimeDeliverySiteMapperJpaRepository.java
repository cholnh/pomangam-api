package com.mrporter.pomangam.client.repositories.ordertime;

import com.mrporter.pomangam.client.domains.ordertime.OrderTimeDeliverySiteMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(exported = false)
public interface OrderTimeDeliverySiteMapperJpaRepository extends JpaRepository<OrderTimeDeliverySiteMapper, Long>, OrderTimeDeliverySiteMapperCustomRepository {

}

interface OrderTimeDeliverySiteMapperCustomRepository {

}

@Transactional(readOnly = true)
class OrderTimeDeliverySiteMapperCustomRepositoryImpl extends QuerydslRepositorySupport implements OrderTimeDeliverySiteMapperCustomRepository {

    public OrderTimeDeliverySiteMapperCustomRepositoryImpl() {
        super(OrderTimeDeliverySiteMapper.class);
    }


}