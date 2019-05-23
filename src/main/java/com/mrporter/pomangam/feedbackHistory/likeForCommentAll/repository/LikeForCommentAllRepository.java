package com.mrporter.pomangam.feedbackHistory.likeForCommentAll.repository;

public interface LikeForCommentAllRepository {
    Byte getType(Integer comment_store_idx, Integer customerIdx);
    void like(Integer comment_store_idx, Integer customerIdx);
    void unlike(Integer comment_store_idx, Integer customerIdx);
}
