package com.mrporter.pomangam.test.data.storeCategory;

import com.mrporter.pomangam.client.domains.store.category.StoreCategory;
import com.mrporter.pomangam.client.repositories.store.category.StoreCategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StoreCategoryData {

    @Autowired
    StoreCategoryJpaRepository storeCategoryJpaRepository;

    @Transactional
    public void of(Long idx, String title) {
        StoreCategory category = StoreCategory.builder()
                .idx(idx)
                .categoryTitle(title)
                .build();
        storeCategoryJpaRepository.save(category);
    }
}