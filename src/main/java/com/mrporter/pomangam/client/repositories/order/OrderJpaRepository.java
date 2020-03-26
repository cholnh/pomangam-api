package com.mrporter.pomangam.client.repositories.order;

import com.mrporter.pomangam.client.domains.order.Order;
import com.mrporter.pomangam.client.domains.order.OrderType;
import com.mrporter.pomangam.client.domains.order.QOrder;
import com.mrporter.pomangam.client.domains.order.item.QOrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;


@RepositoryRestResource(exported = false)
public interface OrderJpaRepository extends JpaRepository<Order, Long>, OrderCustomRepository {
    Page<Order> findAllByIsActiveIsTrue(Pageable pageable); // N+1
    Optional<Order> findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
    Page<Order> findByOrderer_User_PhoneNumberAndIsActiveIsTrue(String phoneNumber, Pageable pageable);
    Optional<Order> findByIdxAndOrderTypeAndIsActiveIsTrue(Long idx, OrderType orderType);
}

interface OrderCustomRepository {

    /**
     * 누적 주문량 계산
     *
     * 받는 날짜(oDate), 받는 장소(dIdx), 받는 시간(oIdx) 에 해당하는 주문내역을
     * 검사하여 주문량 합계를 계산 후 반환.
     *
     * @param dIdx 받는 날짜 (deliverySite 인덱스)
     * @param oIdx 받는 시간 (orderTime 인덱스)
     * @param oDate 받는 날짜 (LocalDate)
     * @return 누적 주문량 합계
     */
    int accumulatedOrderVolume(Long dIdx, Long sIdx, Long oIdx, LocalDate oDate);

    /**
     * 주문 식별 번호 생성
     */
    Short boxNumber(Long dIdx, Long oIdx, LocalDate oDate);
}

@Transactional(readOnly = true)
class OrderCustomRepositoryImpl extends QuerydslRepositorySupport implements OrderCustomRepository {

    public OrderCustomRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public int accumulatedOrderVolume(Long dIdx, Long sIdx, Long oIdx, LocalDate oDate) {
        final QOrder order = QOrder.order;
        final QOrderItem item = QOrderItem.orderItem;

        Short result = from(order)
                .select(item.quantity.sum())
                .leftJoin(order.orderItems, item)
                .where(order.deliveryDetailSite.deliverySite.idx.eq(dIdx)
                .and(order.deliveryDetailSite.deliverySite.isActive.isTrue())
                .and(order.orderDate.eq(oDate))
                .and(order.orderTime.idx.eq(oIdx))
                .and(order.orderType.eq(OrderType.DELIVERY_READY))
                .and(order.isActive.isTrue())
                .and(item.store.idx.eq(sIdx))
                .and(item.isActive.isTrue()))
                .fetchFirst();
        return result == null ? 0 : result;
    }

    @Override
    public Short boxNumber(Long ddIdx, Long oIdx, LocalDate oDate) {
        final QOrder order = QOrder.order;

        Short result = from(order)
                .select(order.boxNumber.max())
                .where(order.deliveryDetailSite.idx.eq(ddIdx)
                        .and(order.deliveryDetailSite.deliverySite.isActive.isTrue())
                        .and(order.orderDate.eq(oDate))
                        .and(order.orderTime.idx.eq(oIdx))
                        .and(order.isActive.isTrue()))
                .fetchFirst();
        return result == null ? 0 : (short)(result + 1);
    }
}
