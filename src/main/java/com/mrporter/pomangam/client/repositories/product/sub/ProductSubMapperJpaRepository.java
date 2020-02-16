package com.mrporter.pomangam.client.repositories.product.sub;

import com.mrporter.pomangam.client.domains.product.sub.ProductSubMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface ProductSubMapperJpaRepository extends JpaRepository<ProductSubMapper, Long>, ProductSubMapperCustomRepository {

}

interface ProductSubMapperCustomRepository {

}

@Transactional(readOnly = true)
class ProductSubMapperCustomRepositoryImpl extends QuerydslRepositorySupport implements ProductSubMapperCustomRepository {

    public ProductSubMapperCustomRepositoryImpl() {
        super(ProductSubMapper.class);
    }


}