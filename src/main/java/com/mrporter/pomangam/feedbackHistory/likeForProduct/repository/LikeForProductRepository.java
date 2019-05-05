package com.mrporter.pomangam.feedbackHistory.likeForProduct.repository;

public interface LikeForProductRepository {
    Byte getType(Integer productIdx, Integer customerIdx);
    void like(Integer productIdx, Integer customerIdx);
    void unlike(Integer productIdx, Integer customerIdx);
}
