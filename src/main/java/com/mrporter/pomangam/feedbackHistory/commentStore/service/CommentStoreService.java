package com.mrporter.pomangam.feedbackHistory.commentStore.service;

import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreViewDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;

import java.util.List;

public interface CommentStoreService {
    List<CommentStoreViewDto> findByStoreIdx(Integer storeIdx, String orderBy, PageRequest pageRequest);
}
