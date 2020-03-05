package com.mrporter.pomangam.client.repositories.ordertime;

import com.mrporter.pomangam.client.domains.ordertime.OrderTime;
import com.mrporter.pomangam.client.domains.ordertime.QOrderTime;
import com.mrporter.pomangam.client.domains.ordertime.QOrderTimeMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RepositoryRestResource(exported = false)
public interface OrderTimeJpaRepository extends JpaRepository<OrderTime, Long>, OrderTimeCustomRepository {

}

interface OrderTimeCustomRepository {
    List<OrderTime> findByIdxDeliverySiteAndIsActiveIsTrue(Long dIdx);
}

@Transactional(readOnly = true)
class OrderTimeCustomRepositoryImpl extends QuerydslRepositorySupport implements OrderTimeCustomRepository {

    public OrderTimeCustomRepositoryImpl() {
        super(OrderTime.class);
    }

    @Override
    public List<OrderTime> findByIdxDeliverySiteAndIsActiveIsTrue(Long dIdx) {
        final QOrderTimeMapper mapper = QOrderTimeMapper.orderTimeMapper;
        final QOrderTime orderTime = QOrderTime.orderTime;
        return from(mapper)
                .select(orderTime)
                .leftJoin(mapper.orderTime, orderTime)
                .where(mapper.store.idxDeliverySite.eq(dIdx)
                .and(mapper.isActive.isTrue())
                .and(mapper.store.isActive.isTrue())
                .and(orderTime.isActive.isTrue()))
                .groupBy(orderTime.arrivalTime)
                .orderBy(orderTime.arrivalTime.asc())
                .fetch();
    }
}