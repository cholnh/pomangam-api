package com.mrporter.pomangam.test.data.storeMapper;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.product.Product;
import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import com.mrporter.pomangam.client.domains.product.sub.ProductSubMapper;
import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.StoreMapper;
import com.mrporter.pomangam.client.repositories.product.sub.ProductSubMapperJpaRepository;
import com.mrporter.pomangam.client.repositories.store.StoreMapperJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StoreMapperData {

    @Autowired
    StoreMapperJpaRepository storeMapperJpaRepository;

    @Transactional
    public void of(Long dIdx, Long ...sIdxes) {
        for(Long sIdx : sIdxes) {
            StoreMapper mapper = StoreMapper.builder()
                    .store(Store.builder().idx(sIdx).build())
                    .deliverySite(DeliverySite.builder().idx(dIdx).build())
                    .build();
            storeMapperJpaRepository.save(mapper);
        }
    }
}