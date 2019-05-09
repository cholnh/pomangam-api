package com.mrporter.pomangam.feedbackHistory.commentAll.service;

import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllViewDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.repository.CommentAllRepositoryImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentAllServiceImpl implements CommentAllService {

    CommentAllRepositoryImpl commentAllRepository;

    @Override
    public CommentAllViewDto getBy(Integer deliverySiteIdx, Integer storeIdx, String orderBy, PageRequest pageRequest) {
        CommentAllViewDto dto = new CommentAllViewDto();
        if(deliverySiteIdx == null) {
            dto.setCommentAlls(commentAllRepository.getAll(orderBy, pageRequest));
        } else {
            dto.setStoreCategories(commentAllRepository.getStoreCategory(deliverySiteIdx));
            dto.setCommentAlls(commentAllRepository.getBy(deliverySiteIdx, storeIdx, orderBy, pageRequest));
        }
        return dto;
    }
}
