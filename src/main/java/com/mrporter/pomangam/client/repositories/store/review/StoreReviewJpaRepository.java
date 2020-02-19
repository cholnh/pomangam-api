package com.mrporter.pomangam.client.repositories.store.review;

import com.mrporter.pomangam.client.domains.store.review.StoreReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface StoreReviewJpaRepository extends JpaRepository<StoreReview, Long>, StoreReviewCustomRepository {
    Page<StoreReview> findByIdxStoreAndIsActiveIsTrue(Long idxStore, Pageable pageable);
    StoreReview findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
}

interface StoreReviewCustomRepository {

}

@Transactional(readOnly = true)
class StoreReviewCustomRepositoryImpl extends QuerydslRepositorySupport implements StoreReviewCustomRepository {

    public StoreReviewCustomRepositoryImpl() {
        super(StoreReview.class);
    }

}