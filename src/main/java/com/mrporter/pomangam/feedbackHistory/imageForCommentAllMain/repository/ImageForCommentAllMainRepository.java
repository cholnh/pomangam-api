package com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.repository;

import com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.domain.ImageForCommentAllMainWithCommentAllDto;

import java.util.List;

public interface ImageForCommentAllMainRepository {
    List<ImageForCommentAllMainWithCommentAllDto> getImageForCommentAllMainByDeliverySiteIdx(Integer delivery_site_idx);
}
