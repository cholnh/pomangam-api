package com.mrporter.pomangam.feedbackHistory.commentAll.domain;

import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain.ReplyForCommentAllDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class CommentAllDetailViewDto implements Serializable {

    CommentAllDetailDto commentAllDetail;
    List<ReplyForCommentAllDto> replies;

    @Builder
    public CommentAllDetailViewDto(CommentAllDetailDto commentAllDetail, List<ReplyForCommentAllDto> replies) {
        this.commentAllDetail = commentAllDetail;
        this.replies = replies;
    }
}