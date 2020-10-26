package com.mrporter.pomangam.client.services.store.review;

import com.mrporter.pomangam.client.domains.store.review.StoreReviewDto;
import com.mrporter.pomangam.client.domains.store.review.StoreReviewSortType;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreReviewService {
    List<StoreReviewDto> findByIdxStore(Long sIdx, Long uIdx, StoreReviewSortType sortType, Pageable pageable);
    StoreReviewDto findByIdx(Long idx, Long uIdx);
    long count();
    StoreReviewDto save(StoreReviewDto dto, List<MultipartFile> images, String idxesOrderItem);
    StoreReviewDto update(StoreReviewDto dto, List<MultipartFile> images);
    void delete(Long dIdx, Long sIdx, Long idx);
}
