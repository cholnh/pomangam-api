package com.mrporter.pomangam.client.repositories.store.review;

import com.mrporter.pomangam.client.domains.store.review.QStoreReview;
import com.mrporter.pomangam.client.domains.store.review.StoreReview;
import com.mrporter.pomangam.client.domains.store.review.StoreReviewSortType;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    Page<StoreReview> findByIdxStore(Long idxStore, StoreReviewSortType sortType, Pageable pageable);
}

@Transactional(readOnly = true)
class StoreReviewCustomRepositoryImpl extends QuerydslRepositorySupport implements StoreReviewCustomRepository {

    public StoreReviewCustomRepositoryImpl() {
        super(StoreReview.class);
    }

    public Page<StoreReview> findByIdxStore(Long idxStore, StoreReviewSortType sortType, Pageable pageable) {
        QStoreReview storeReview = QStoreReview.storeReview;

        JPQLQuery<StoreReview> query =
                from(storeReview)
                .select(storeReview)
                .where(storeReview.idxStore.eq(idxStore)
                .and(storeReview.isActive.isTrue()))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset());

        switch (sortType) {
            case SORT_BY_DATE_DESC:
                query.orderBy(storeReview.modifyDate.desc());
                break;
            case SORT_BY_DATE_ASC:
                query.orderBy(storeReview.modifyDate.asc());
                break;
            case SORT_BY_STAR_DESC:
                query.orderBy(storeReview.star.desc());
                break;
            case SORT_BY_STAR_ASC:
                query.orderBy(storeReview.star.asc());
                break;
        }
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }
}