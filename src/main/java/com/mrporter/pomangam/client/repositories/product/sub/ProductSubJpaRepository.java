package com.mrporter.pomangam.client.repositories.product.sub;

import com.mrporter.pomangam.client.domains.product.sub.ProductSub;
import com.mrporter.pomangam.client.domains.product.sub.QProductSub;
import com.mrporter.pomangam.client.domains.product.sub.QProductSubMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RepositoryRestResource(exported = false)
public interface ProductSubJpaRepository extends JpaRepository<ProductSub, Long>, ProductSubCustomRepository {
    ProductSub findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
}

interface ProductSubCustomRepository {
    List<ProductSub> findByIdxProductAndIsActiveIsTrue(Long pIdx);
}

@Transactional(readOnly = true)
class ProductSubCustomRepositoryImpl extends QuerydslRepositorySupport implements ProductSubCustomRepository {

    public ProductSubCustomRepositoryImpl() {
        super(ProductSub.class);
    }

    @Override
    public List<ProductSub> findByIdxProductAndIsActiveIsTrue(Long pIdx) {
        final QProductSubMapper mapper = QProductSubMapper.productSubMapper;
        final QProductSub productSub = QProductSub.productSub;
        return from(productSub)
                .join(mapper).on(productSub.idx.eq(mapper.productSub.idx))
                .where(mapper.product.idx.eq(pIdx)
                .and(mapper.isActive.isTrue())
                .and(productSub.isActive.isTrue()))
                .fetch();
    }
}