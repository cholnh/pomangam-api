package com.mrporter.pomangam.client.services.store.review.like;

import com.mrporter.pomangam.client.domains.store.review.StoreReview;
import com.mrporter.pomangam.client.domains.store.review.like.StoreReviewLike;
import com.mrporter.pomangam.client.repositories.store.review.StoreReviewJpaRepository;
import com.mrporter.pomangam.client.repositories.store.review.like.StoreReviewLikeJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreReviewLikeServiceImpl implements StoreReviewLikeService {

    StoreReviewLikeJpaRepository storeReviewLikeRepo;
    StoreReviewJpaRepository storeReviewRepo;
    UserJpaRepository userRepo;

    @Override
    public boolean toggle(String phoneNumber, Long rIdx) {
        Long uIdx = userRepo.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        boolean like = storeReviewLikeRepo.existsByIdxUser(uIdx);
        if(like) {
            cancelLike(uIdx, rIdx);
        } else {
            like(uIdx, rIdx);
        }
        return !like;
    }

    @Override
    public void like(String phoneNumber, Long rIdx) {
        Long uIdx = userRepo.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        like(uIdx, rIdx);
    }

    @Override
    public void cancelLike(String phoneNumber, Long rIdx) {
        Long uIdx = userRepo.findIdxByPhoneNumberAndIsActiveIsTrue(phoneNumber);
        cancelLike(uIdx, rIdx);
    }

    private void like(Long uIdx, Long rIdx) {
        saveLike(uIdx, rIdx);
        addCntLike(rIdx);
    }

    private void cancelLike(Long uIdx, Long rIdx) {
        deleteLike(uIdx, rIdx);
        subCntLike(rIdx);
    }

    private void saveLike(Long uIdx, Long rIdx) {
        StoreReviewLike like = StoreReviewLike.builder()
                .idxStoreReview(rIdx)
                .idxUser(uIdx)
                .build();
        storeReviewLikeRepo.save(like);
    }

    private void deleteLike(Long uIdx, Long rIdx) {
        storeReviewLikeRepo.deleteByIdxUserAndIdxStoreReviewQuery(uIdx, rIdx);
    }

    private void addCntLike(Long rIdx) {
        StoreReview storeReview = storeReviewRepo.findByIdxAndIsActiveIsTrue(rIdx);
        storeReview.addCntLike();
        storeReviewRepo.save(storeReview);
    }
    private void subCntLike(Long rIdx) {
        StoreReview storeReview = storeReviewRepo.findByIdxAndIsActiveIsTrue(rIdx);
        storeReview.subCntLike();
        storeReviewRepo.save(storeReview);
    }
}
