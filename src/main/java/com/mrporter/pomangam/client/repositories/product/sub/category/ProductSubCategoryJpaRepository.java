package com.mrporter.pomangam.client.repositories.product.sub.category;

import com.mrporter.pomangam.client.domains.product.sub.category.ProductSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface ProductSubCategoryJpaRepository extends JpaRepository<ProductSubCategory, Long>, ProductSubCategoryCustomRepository {

}

interface ProductSubCategoryCustomRepository {

}

@Transactional(readOnly = true)
class ProductSubCategoryCustomRepositoryImpl extends QuerydslRepositorySupport implements ProductSubCategoryCustomRepository {

    public ProductSubCategoryCustomRepositoryImpl() {
        super(ProductSubCategory.class);
    }


}
