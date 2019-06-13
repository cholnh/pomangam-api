package com.mrporter.pomangam.feedbackHistory.replyForCommentAll.service;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.service.UserService;
import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.feedbackHistory.likeForReplyAll.repository.LikeForReplyAllRepositoryImpl;
import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain.ReplyForCommentAll;
import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain.ReplyForCommentAllDto;
import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.domain.ReplyForCommentAllInputDto;
import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.repository.ReplyForCommentAllJpaRepository;
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
    ReplyForCommentAllJpaRepository replyForCommentAllJpaRepository;
    LikeForReplyAllRepositoryImpl likeForReplyAllRepository;

    @Override
    public List<ReplyForCommentAllDto> getBy(Integer commentIdx, String customerId, PageRequest pageRequest) {
        User user = userService.findById(customerId);
        if(user == null) {
            return replyForCommentAllRepository.getBy(commentIdx, null, pageRequest);
        } else {
            return replyForCommentAllRepository.getBy(commentIdx, user.getIdx(), pageRequest);
        }
    }

    @Override
    public ReplyForCommentAll saveReplyForCommentAllInput(ReplyForCommentAllInputDto dto) {
        ReplyForCommentAll comment = ReplyForCommentAll.builder()
                .comment_all_idx(dto.getCommentAllIdx())
                .customer_idx(dto.getCustomer_idx())
                .owner_idx(dto.getOwner_idx())
                .register_date(CustomTime.curTimestampSql())
                .contents(dto.getContents())
                .state_active(Byte.valueOf("1"))
                .state_anonymous(dto.getIsAnonymous()?Byte.valueOf("1"):Byte.valueOf("0"))
                .cnt_like(0)
                .cnt_unlike(0)
                .build();
        return replyForCommentAllJpaRepository.save(comment);
    }

    @Override
    public void like(Integer replyIdx, String customerId) {
        final User user = userService.findById(customerId);
        if(user != null) {
            likeForReplyAllRepository.like(replyIdx, user.getIdx());
        }
    }

    @Override
    public void unlike(Integer replyIdx, String customerId) {
        final User user = userService.findById(customerId);
        if(user != null) {
            likeForReplyAllRepository.unlike(replyIdx, user.getIdx());
        }
    }

    @Override
    public ReplyForCommentAll patch(Integer replyIdx, ReplyForCommentAllInputDto dto) {
        final ReplyForCommentAll fetched = replyForCommentAllJpaRepository.getOne(replyIdx);
        if (fetched == null) {
            return null;
        }

        if (dto.getCommentAllIdx() != null) {
            fetched.setComment_all_idx(dto.getCommentAllIdx());
        }
        if (dto.getCustomer_idx() != null) {
            fetched.setCustomer_idx(dto.getCustomer_idx());
        }
        if (dto.getIsAnonymous() != null) {
            fetched.setState_anonymous(dto.getIsAnonymous()?Byte.valueOf("1"):Byte.valueOf("0"));
        }
        if (dto.getOwner_idx() != null) {
            fetched.setOwner_idx(dto.getOwner_idx());
        }
        if (dto.getContents() != null) {
            fetched.setContents(dto.getContents());
        }
        fetched.setModify_date(CustomTime.curTimestampSql());
        return replyForCommentAllJpaRepository.save(fetched);
    }

    @Override
    public Boolean delete(Integer replyIdx) {
        final ReplyForCommentAll fetched = replyForCommentAllJpaRepository.getOne(replyIdx);
        if (fetched == null) {
            return false;
        } else {
            replyForCommentAllJpaRepository.delete(fetched);
            return true;
        }
    }
}
