package com.mrporter.pomangam.client.repositories.deliverysite.region;

import com.mrporter.pomangam.client.domains.deliverysite.region.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface RegionJpaRepository extends JpaRepository<Region, Long>, RegionCustomRepository {
    Page<Region> findAllByIsActiveIsTrue(Pageable pageable);
    Region findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
}

interface RegionCustomRepository {

}

@Transactional(readOnly = true)
class RegionCustomRepositoryImpl extends QuerydslRepositorySupport implements RegionCustomRepository {

    public RegionCustomRepositoryImpl() {
        super(Region.class);
    }


}
