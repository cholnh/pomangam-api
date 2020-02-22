package com.mrporter.pomangam.client.repositories.order;

import com.mrporter.pomangam.client.domains.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface OrderJpaRepository extends JpaRepository<Order, Long>, OrderCustomRepository {
    Page<Order> findAllByIsActiveIsTrue(Pageable pageable);
    Order findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
}

interface OrderCustomRepository {

}

@Transactional(readOnly = true)
class OrderCustomRepositoryImpl extends QuerydslRepositorySupport implements OrderCustomRepository {

    public OrderCustomRepositoryImpl() {
        super(Order.class);
    }


}
