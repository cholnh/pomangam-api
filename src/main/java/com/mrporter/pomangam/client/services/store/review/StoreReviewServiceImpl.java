package com.mrporter.pomangam.client.services.store.review;

import com.mrporter.pomangam.client.domains.store.review.StoreReviewDto;
import com.mrporter.pomangam.client.repositories.store.review.StoreReviewJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreReviewServiceImpl implements StoreReviewService {
    StoreReviewJpaRepository storeReviewJpaRepository;

    @Override
    public List<StoreReviewDto> findByIdxStore(Long sIdx, Pageable pageable) {
        return StoreReviewDto.fromEntities(storeReviewJpaRepository.findByIdxStore(sIdx, pageable).getContent());
    }

    @Override
    public StoreReviewDto findByIdx(Long idx) {
        return StoreReviewDto.fromEntity(storeReviewJpaRepository.findById(idx).get());
    }

    @Override
    public long count() {
        return storeReviewJpaRepository.count();
    }
}
