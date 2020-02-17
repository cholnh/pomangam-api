package com.mrporter.pomangam.client.services.store.like;

public interface StoreLikeService {
    boolean toggle(String phoneNumber, Long sIdx);
    void like(String phoneNumber, Long sIdx);
    void cancelLike(String phoneNumber, Long sIdx);
}
