package com.mrporter.pomangam.feedbackHistory.replyForCommentAll.service;

import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain.ReplyForCommentAll;
import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain.ReplyForCommentAllDto;
import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain.ReplyForCommentAllInputDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;

import java.util.List;

public interface ReplyForCommentAllService {
    List<ReplyForCommentAllDto> getBy(Integer commentIdx, String customerId, PageRequest pageRequest);
    ReplyForCommentAll saveReplyForCommentAllInput(ReplyForCommentAllInputDto dto);

    ReplyForCommentAll patch(Integer replyIdx, ReplyForCommentAllInputDto dto);
    Boolean delete(Integer replyIdx);

    void like(Integer replyIdx, String customerId);
    void unlike(Integer replyIdx, String customerId);
}
