package com.mrporter.pomangam.feedbackHistory.commentAll.repository;

import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllDetailDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.StoreCategoryDto;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;

import java.util.List;

public interface CommentAllRepository {
    List<CommentAllDto> getAll(String orderBy, PageRequest pageRequest);
    List<CommentAllDto> getBy(Integer deliverySiteIdx, Integer storeIdx, String orderBy, PageRequest pageRequest);
    List<StoreCategoryDto> getStoreCategory(Integer deliverySiteIdx);
    CommentAllDetailDto getDetail(Integer commentIdx, Integer customerId);
}
