package com.mrporter.pomangam.feedbackHistory.likeForReplyAll.repository;

public interface LikeForReplyAllRepository {
    Byte getType(Integer comment_store_idx, Integer customerIdx);
    void like(Integer comment_store_idx, Integer customerIdx);
    void unlike(Integer comment_store_idx, Integer customerIdx);
}
