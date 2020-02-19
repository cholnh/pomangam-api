package com.mrporter.pomangam.client.repositories.product;

import com.mrporter.pomangam.client.domains.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(exported = false)
public interface ProductJpaRepository extends JpaRepository<Product, Long>, ProductCustomRepository {
    Page<Product> findByIdxStoreAndIsActiveIsTrueOrderBySequenceAsc(Long idxStore, Pageable pageable);
    Product findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
}

interface ProductCustomRepository {

}

@Transactional(readOnly = true)
class ProductCustomRepositoryImpl extends QuerydslRepositorySupport implements ProductCustomRepository {

    public ProductCustomRepositoryImpl() {
        super(Product.class);
    }

}