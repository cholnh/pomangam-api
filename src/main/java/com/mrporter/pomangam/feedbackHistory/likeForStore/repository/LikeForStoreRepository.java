package com.mrporter.pomangam.feedbackHistory.likeForStore.repository;

public interface LikeForStoreRepository {
    Byte getType(Integer storeIdx, Integer customerIdx);
    void like(Integer storeIdx, Integer customerIdx);
    void unlike(Integer storeIdx, Integer customerIdx);
}
