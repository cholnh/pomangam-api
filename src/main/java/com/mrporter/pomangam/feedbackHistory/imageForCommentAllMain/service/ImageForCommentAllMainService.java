package com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.service;

import com.mrporter.pomangam.feedbackHistory.imageForCommentAllMain.domain.ImageForCommentAllMainWithCommentAllDto;

import java.util.List;

public interface ImageForCommentAllMainService {
    List<ImageForCommentAllMainWithCommentAllDto> getImageForCommentAllMainByDeliverySiteIdx(Integer delivery_site_idx);
}
