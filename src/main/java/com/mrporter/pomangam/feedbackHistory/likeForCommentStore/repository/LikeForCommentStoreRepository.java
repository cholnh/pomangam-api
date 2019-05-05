package com.mrporter.pomangam.feedbackHistory.likeForCommentStore.repository;

public interface LikeForCommentStoreRepository {
    Byte getType(Integer comment_store_idx, Integer customerIdx);
    void like(Integer comment_store_idx, Integer customerIdx);
    void unlike(Integer comment_store_idx, Integer customerIdx);
}
