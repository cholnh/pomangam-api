package com.mrporter.pomangam.feedbackHistory.commentAll.service;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.service.UserService;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllDetailDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllViewDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.repository.CommentAllRepositoryImpl;
import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.repository.ReplyForCommentAllRepositoryImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentAllServiceImpl implements CommentAllService {

    UserService userService;
    CommentAllRepositoryImpl commentAllRepository;
    ReplyForCommentAllRepositoryImpl replyForCommentAllRepository;

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

    @Override
    public CommentAllDetailDto getDetail(Integer commentIdx, String customerId) {
        User user = userService.findById(customerId);
        if(user == null) {
            return commentAllRepository.getDetail(commentIdx, null);
        } else {
            return commentAllRepository.getDetail(commentIdx, user.getIdx());
        }

    }
}
