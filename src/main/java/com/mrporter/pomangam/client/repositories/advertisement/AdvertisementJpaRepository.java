package com.mrporter.pomangam.client.repositories.advertisement;

import com.mrporter.pomangam.client.domains.advertisement.Advertisement;
import com.mrporter.pomangam.client.domains.advertisement.QAdvertisement;
import com.mrporter.pomangam.client.domains.advertisement.QAdvertisementMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RepositoryRestResource(exported = false)
public interface AdvertisementJpaRepository extends JpaRepository<Advertisement, Long>, AdvertisementCustomRepository {
}

interface AdvertisementCustomRepository {
    Page<Advertisement> findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(Long dIdx, Pageable pageable); // N+1 문제 해결
}

@Transactional(readOnly = true)
class AdvertisementCustomRepositoryImpl extends QuerydslRepositorySupport implements AdvertisementCustomRepository {

    public AdvertisementCustomRepositoryImpl() {
        super(Advertisement.class);
    }

    @Override
    public Page<Advertisement> findFetchJoinByIdxDeliverySiteAndIsActiveIsTrue(Long dIdx, Pageable pageable) {
        QAdvertisement advertisement = QAdvertisement.advertisement;
        QAdvertisementMapper advertisementMapper = QAdvertisementMapper.advertisementMapper;
        List<Advertisement> results =
                from(advertisementMapper)
                .select(advertisement)
                .leftJoin(advertisementMapper.advertisement, advertisement)
                .where(advertisementMapper.deliverySite.idx.eq(dIdx))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        return new PageImpl<>(results, pageable, results.size());
    }
}
