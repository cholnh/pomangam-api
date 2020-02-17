package com.mrporter.pomangam.client.services.store.review;

import com.mrporter.pomangam.client.domains.store.review.StoreReviewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreReviewService {
    List<StoreReviewDto> findByIdxStore(Long sIdx, Pageable pageable);
    StoreReviewDto findByIdx(Long idx);
    long count();
}
