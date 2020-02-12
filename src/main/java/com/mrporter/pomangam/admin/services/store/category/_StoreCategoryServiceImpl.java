package com.mrporter.pomangam.admin.services.store.category;

import com.mrporter.pomangam.admin.repositories.store.category._StoreCategoryJpaRepository;
import com.mrporter.pomangam.client.repositories.store.category.StoreCategoryJpaRepository;
import com.mrporter.pomangam.client.services.store.category.StoreCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class _StoreCategoryServiceImpl implements _StoreCategoryService {
    _StoreCategoryJpaRepository storeCategoryJpaRepository;

}
