package com.mrporter.pomangam.client.services.store.category;

import com.mrporter.pomangam.client.repositories.store.category.StoreCategoryJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreCategoryServiceImpl implements StoreCategoryService {
    StoreCategoryJpaRepository storeCategoryJpaRepository;


}
