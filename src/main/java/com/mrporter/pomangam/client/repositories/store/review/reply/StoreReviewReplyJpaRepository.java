package com.mrporter.pomangam.client.repositories.store.review.reply;

import com.mrporter.pomangam.client.domains.store.review.reply.StoreReviewReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface StoreReviewReplyJpaRepository extends JpaRepository<StoreReviewReply, Long>, StoreReviewReplyCustomRepository {
    Page<StoreReviewReply> findByIdxStoreReviewAndIsActiveIsTrue(Long idxStoreReview, Pageable pageable);
    StoreReviewReply findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
}

interface StoreReviewReplyCustomRepository {

}

@Transactional(readOnly = true)
class StoreReviewReplyCustomRepositoryImpl extends QuerydslRepositorySupport implements StoreReviewReplyCustomRepository {

    public StoreReviewReplyCustomRepositoryImpl() {
        super(StoreReviewReply.class);
    }


}