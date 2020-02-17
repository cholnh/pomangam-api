package com.mrporter.pomangam.client.services.store.review.like;

public interface StoreReviewLikeService {
    boolean toggle(String phoneNumber, Long rIdx);
    void like(String phoneNumber, Long rIdx);
    void cancelLike(String phoneNumber, Long rIdx);
}
