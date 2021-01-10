package com.mrporter.pomangam.client.repositories.promotion;

import com.mrporter.pomangam.client.domains.promotion.PromotionMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface PromotionMapperJpaRepository extends JpaRepository<PromotionMapper, Long>, PromotionMapperCustomRepository {

}

interface PromotionMapperCustomRepository {

}

@Transactional(readOnly = true)
class PromotionMapperCustomRepositoryImpl extends QuerydslRepositorySupport implements PromotionMapperCustomRepository {

    public PromotionMapperCustomRepositoryImpl() {
        super(PromotionMapper.class);
    }


}
