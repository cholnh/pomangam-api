package com.mrporter.pomangam.client.services.product.reply.like;

public interface ProductReplyLikeService {
    boolean toggle(String phoneNumber, Long prIdx);
    void like(String phoneNumber, Long prIdx);
    void cancelLike(String phoneNumber, Long prIdx);
}
