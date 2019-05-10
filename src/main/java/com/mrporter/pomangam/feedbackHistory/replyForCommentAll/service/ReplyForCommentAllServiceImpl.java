package com.mrporter.pomangam.feedbackHistory.replyForCommentAll.service;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.service.UserService;
import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain.ReplyForCommentAllDto;
import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.repository.ReplyForCommentAllRepositoryImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReplyForCommentAllServiceImpl implements ReplyForCommentAllService {

    UserService userService;
    ReplyForCommentAllRepositoryImpl replyForCommentAllRepository;

    @Override
    public List<ReplyForCommentAllDto> getBy(Integer commentIdx, String customerId, PageRequest pageRequest) {
        User user = userService.findById(customerId);
        if(user == null) {
            return replyForCommentAllRepository.getBy(commentIdx, null, pageRequest);
        } else {
            return replyForCommentAllRepository.getBy(commentIdx, user.getIdx(), pageRequest);
        }

    }
}
