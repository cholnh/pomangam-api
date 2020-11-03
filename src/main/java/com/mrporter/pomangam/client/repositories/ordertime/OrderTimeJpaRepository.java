package com.mrporter.pomangam.client.repositories.ordertime;

import com.mrporter.pomangam.client.domains.ordertime.OrderTime;
import com.mrporter.pomangam.client.domains.ordertime.QOrderTime;
import com.mrporter.pomangam.client.domains.ordertime.QOrderTimeDeliverySiteMapper;
import com.mrporter.pomangam.client.domains.ordertime.QOrderTimeMapper;
import com.mrporter.pomangam.client.domains.store.QStore;
import com.mrporter.pomangam.client.domains.store.QStoreMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface OrderTimeJpaRepository extends JpaRepository<OrderTime, Long>, OrderTimeCustomRepository {

}

interface OrderTimeCustomRepository {
    List<OrderTime> findByIdxDeliverySite(Long dIdx);
    List<OrderTime> findByIdxStore(Long sIdx);
}

@Transactional(readOnly = true)
class OrderTimeCustomRepositoryImpl extends QuerydslRepositorySupport implements OrderTimeCustomRepository {

    public OrderTimeCustomRepositoryImpl() {
        super(OrderTime.class);
    }

    @Override
    public List<OrderTime> findByIdxDeliverySite(Long dIdx) {
        final QOrderTimeDeliverySiteMapper mapper = QOrderTimeDeliverySiteMapper.orderTimeDeliverySiteMapper;
        final QOrderTime orderTime = QOrderTime.orderTime;
        return from(mapper)
                .select(orderTime)
                .leftJoin(mapper.orderTime, orderTime)
                .where(mapper.deliverySite.idx.eq(dIdx)
                .and(mapper.isActive.isTrue())
                .and(mapper.deliverySite.isActive.isTrue())
                .and(orderTime.isActive.isTrue()))
                .groupBy(orderTime.arrivalTime)
                .orderBy(orderTime.arrivalTime.asc())
                .fetch();

//        final QOrderTimeMapper mapper = QOrderTimeMa
//        pper.orderTimeMapper;
//        final QOrderTime orderTime = QOrderTime.orderTime;
//        final QStoreMapper storeMapper = QStoreMapper.storeMapper;
//        return from(mapper)
//                .select(orderTime)
//                .leftJoin(mapper.orderTime, orderTime)
//                .join(storeMapper).on(storeMapper.store.idx.eq(mapper.store.idx))
//                .where(storeMapper.deliverySite.idx.eq(dIdx)
//                .and(mapper.isActive.isTrue())
//                .and(mapper.store.isActive.isTrue())
//                .and(orderTime.isActive.isTrue()))
//                .groupBy(orderTime.arrivalTime)
//                .orderBy(orderTime.arrivalTime.asc())
//                .fetch();
    }

    @Override
    public List<OrderTime> findByIdxStore(Long sIdx) {
        final QOrderTimeMapper mapper = QOrderTimeMapper.orderTimeMapper;
        final QOrderTime orderTime = QOrderTime.orderTime;
        return from(mapper)
                .select(orderTime)
                .leftJoin(mapper.orderTime, orderTime)
                .where(mapper.store.idx.eq(sIdx)
                        .and(mapper.isActive.isTrue())
                        .and(mapper.store.isActive.isTrue())
                        .and(orderTime.isActive.isTrue()))
                .groupBy(orderTime.arrivalTime)
                .orderBy(orderTime.arrivalTime.asc())
                .fetch();
    }
}