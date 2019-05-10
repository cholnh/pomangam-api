package com.mrporter.pomangam.feedbackHistory.replyForCommentAll.repository;

import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain.ReplyForCommentAllDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;

import java.util.List;

public interface ReplyForCommentAllRepository {
    List<ReplyForCommentAllDto> getBy(Integer commentIdx, Integer customerIdx, PageRequest pageRequest);
}
