package com.mrporter.pomangam.client.services.store.review.reply.like;

public interface StoreReviewReplyLikeService {
    boolean toggle(String phoneNumber, Long srIdx);
    void like(String phoneNumber, Long srIdx);
    void cancelLike(String phoneNumber, Long srIdx);
}
