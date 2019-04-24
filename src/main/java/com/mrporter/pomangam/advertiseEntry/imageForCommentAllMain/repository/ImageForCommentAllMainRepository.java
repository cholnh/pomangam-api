package com.mrporter.pomangam.advertiseEntry.imageForCommentAllMain.repository;

import com.mrporter.pomangam.advertiseEntry.imageForCommentAllMain.domain.ImageForCommentAllMainWithCommentAllDto;

import java.util.List;

public interface ImageForCommentAllMainRepository {
    List<ImageForCommentAllMainWithCommentAllDto> getImageForCommentAllMainByDeliverySiteIdx(Integer delivery_site_idx);
}
