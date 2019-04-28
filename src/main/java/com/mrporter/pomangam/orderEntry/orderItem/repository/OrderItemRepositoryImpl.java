package com.mrporter.pomangam.orderEntry.orderItem.repository;

import com.mrporter.pomangam.orderEntry.orderItem.domain.OrderInfoItemDto;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<OrderInfoItemDto> findOrderInfoItem(Integer orderIdx) {
        List orderInfoItemDtoList = em
                .createNativeQuery(
            "SELECT item.idx as order_item_idx, prod.name as product_name, item.quantity, item.requirement, item.parent_item_idx, item.unit_amount " +
                    "FROM item_for_order_tbl item, product_tbl prod " +
                    "WHERE item.product_idx = prod.idx AND item.order_idx = ?"
                )
                .setParameter(1, orderIdx)
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( OrderInfoItemDto.class ) )
                .getResultList();
        return orderInfoItemDtoList;
    }
}
