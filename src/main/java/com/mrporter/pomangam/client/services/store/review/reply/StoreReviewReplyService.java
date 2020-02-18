package com.mrporter.pomangam.client.services.store.review.reply;

import com.mrporter.pomangam.client.domains.store.review.reply.StoreReviewReplyDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreReviewReplyService {
    List<StoreReviewReplyDto> findByIdxStoreReview(Long rIdx, Long uIdx, Pageable pageable);
    StoreReviewReplyDto findByIdx(Long idx, Long uIdx);
    long count();
    StoreReviewReplyDto save(StoreReviewReplyDto dto);
    StoreReviewReplyDto update(StoreReviewReplyDto dto);
    void delete(Long rIdx, Long idx);
}
