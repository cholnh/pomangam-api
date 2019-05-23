package com.mrporter.pomangam.feedbackHistory.commentAll.service;

import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAll;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllDetailViewDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllInputDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllViewDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;

public interface CommentAllService {
    CommentAllViewDto getBy(Integer deliverySiteIdx, Integer storeIdx, String orderBy, PageRequest pageRequest);
    CommentAllDetailViewDto getDetail(Integer commentIdx, String customerId);
    CommentAll saveCommentAllInput(CommentAllInputDto dto);

    void like(Integer commentAllIdx, String customerId);
    void unlike(Integer commentAllIdx, String customerId);
}
