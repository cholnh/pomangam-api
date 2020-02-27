package com.mrporter.pomangam.client.repositories.advertisement;

import com.mrporter.pomangam.client.domains.advertisement.AdvertisementMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface AdvertisementMapperJpaRepository extends JpaRepository<AdvertisementMapper, Long>, AdvertisementMapperCustomRepository {

    @Deprecated
    Page<AdvertisementMapper> findAdvertisementByDeliverySite_IdxAndIsActiveIsTrue(Long dIdx, Pageable pageable); // N+1 발생
}

interface AdvertisementMapperCustomRepository {
}

@Transactional(readOnly = true)
class AdvertisementMapperCustomRepositoryImpl extends QuerydslRepositorySupport implements AdvertisementMapperCustomRepository {

    public AdvertisementMapperCustomRepositoryImpl() {
        super(AdvertisementMapper.class);
    }
}