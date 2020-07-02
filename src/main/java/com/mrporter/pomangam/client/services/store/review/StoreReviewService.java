package com.mrporter.pomangam.client.services.store.review;

import com.mrporter.pomangam.client.domains.store.review.StoreReviewDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StoreReviewService {
    List<StoreReviewDto> findByIdxStore(Long sIdx, Long uIdx, Pageable pageable);
    StoreReviewDto findByIdx(Long idx, Long uIdx);
    long count();
    StoreReviewDto save(StoreReviewDto dto);
    StoreReviewDto save(StoreReviewDto dto,  MultipartFile[] images);
    StoreReviewDto update(StoreReviewDto dto,  MultipartFile[] images);
    void delete(Long dIdx, Long sIdx, Long idx);
}
