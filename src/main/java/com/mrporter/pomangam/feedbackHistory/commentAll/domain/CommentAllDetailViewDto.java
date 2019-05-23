package com.mrporter.pomangam.feedbackHistory.commentAll.domain;

import com.mrporter.pomangam.feedbackHistory.imageForCommentAll.domain.ImageForCommentAll;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class CommentAllDetailViewDto implements Serializable {

    CommentAllDetailDto commentAllDetail;
    List<ImageForCommentAll> imageForCommentAlls;

    @Builder
    public CommentAllDetailViewDto(CommentAllDetailDto commentAllDetail, List<ImageForCommentAll> imageForCommentAlls) {
        this.commentAllDetail = commentAllDetail;
        this.imageForCommentAlls = imageForCommentAlls;
    }
}