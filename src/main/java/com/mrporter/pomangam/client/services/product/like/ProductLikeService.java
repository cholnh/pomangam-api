package com.mrporter.pomangam.client.services.product.like;

public interface ProductLikeService {
    boolean toggle(String phoneNumber, Long pIdx);
    void like(String phoneNumber, Long pIdx);
    void cancelLike(String phoneNumber, Long pIdx);
}
