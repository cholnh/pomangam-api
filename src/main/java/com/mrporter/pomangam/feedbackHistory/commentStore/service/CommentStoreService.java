package com.mrporter.pomangam.feedbackHistory.commentStore.service;

import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStore;
import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreInputDto;
import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreViewDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;

import java.util.List;

public interface CommentStoreService {
    List<CommentStoreViewDto> findByStoreIdx(Integer storeIdx, String orderBy, String customerId, PageRequest pageRequest);
    CommentStore saveCommentStoreInput(CommentStoreInputDto dto);
    void delete(Integer commentStoreIdx);

    void like(Integer commentStoreIdx, String customerId);
    void unlike(Integer commentStoreIdx, String customerId);
}
