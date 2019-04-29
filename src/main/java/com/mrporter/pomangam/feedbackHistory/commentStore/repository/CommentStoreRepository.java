package com.mrporter.pomangam.feedbackHistory.commentStore.repository;

import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreViewDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;

import java.util.List;

public interface CommentStoreRepository {
    double getAvgStar(Integer storeIdx);
    List<CommentStoreViewDto> findByStoreIdx(Integer storeIdx, String orderBy, PageRequest pageRequest);
}
