package com.mrporter.pomangam.feedbackHistory.commentAll.service;

import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllDetailDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllViewDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;

public interface CommentAllService {
    CommentAllViewDto getBy(Integer deliverySiteIdx, Integer storeIdx, String orderBy, PageRequest pageRequest);
    CommentAllDetailDto getDetail(Integer commentIdx, String customerId);
}
