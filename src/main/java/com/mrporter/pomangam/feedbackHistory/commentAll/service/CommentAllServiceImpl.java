package com.mrporter.pomangam.feedbackHistory.commentAll.service;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.service.UserService;
import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAll;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllDetailViewDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllInputDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.domain.CommentAllViewDto;
import com.mrporter.pomangam.feedbackHistory.commentAll.repository.CommentAllJpaRepository;
import com.mrporter.pomangam.feedbackHistory.commentAll.repository.CommentAllRepositoryImpl;
import com.mrporter.pomangam.feedbackHistory.imageForCommentAll.repository.ImageForCommentAllJpaRepository;
import com.mrporter.pomangam.feedbackHistory.likeForCommentAll.repository.LikeForCommentAllRepositoryImpl;
import com.mrporter.pomangam.feedbackHistory.replyForCommentAll.repository.ReplyForCommentAllRepositoryImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentAllServiceImpl implements CommentAllService {

    UserService userService;
    CommentAllRepositoryImpl commentAllRepository;
    CommentAllJpaRepository commentAllJpaRepository;
    ReplyForCommentAllRepositoryImpl replyForCommentAllRepository;
    ImageForCommentAllJpaRepository imageForCommentAllJpaRepository;
    LikeForCommentAllRepositoryImpl likeForCommentAllRepository;

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
    public CommentAllDetailViewDto getDetail(Integer commentIdx, String customerId) {
        CommentAllDetailViewDto dto = new CommentAllDetailViewDto();
        User user = userService.findById(customerId);
        if(user == null) {
            dto.setCommentAllDetail(commentAllRepository.getDetail(commentIdx, null));
        } else {
            dto.setCommentAllDetail(commentAllRepository.getDetail(commentIdx, user.getIdx()));
        }
        dto.setImageForCommentAlls(imageForCommentAllJpaRepository.findByCommentAllIdx(commentIdx));
        return dto;
    }

    @Override
    public CommentAll saveCommentAllInput(CommentAllInputDto dto) {
        CommentAll comment = CommentAll.builder()
                .delivery_site_idx(dto.getDeliverySiteIdx())
                .store_idx(dto.getStoreIdx())
                .customer_idx(dto.getCustomerIdx())
                .register_date(CustomTime.curTimestampSql())
                .cnt_like(0)
                .cnt_unlike(0)
                .cnt_view(0)
                .title(dto.getTitle())
                .contents(dto.getContents())
                .state_active(Byte.valueOf("1"))
                .state_anonymous(dto.getIsAnonymous()?Byte.valueOf("1"):Byte.valueOf("0"))
                .build();
        return commentAllJpaRepository.save(comment);
    }

    @Override
    public void like(Integer commentAllIdx, String customerId) {
        final User user = userService.findById(customerId);
        if(user != null) {
            likeForCommentAllRepository.like(commentAllIdx, user.getIdx());
        }
    }

    @Override
    public void unlike(Integer commentAllIdx, String customerId) {
        final User user = userService.findById(customerId);
        if(user != null) {
            likeForCommentAllRepository.unlike(commentAllIdx, user.getIdx());
        }
    }

    @Override
    public CommentAll patch(Integer commentIdx, CommentAllInputDto dto) {
        final CommentAll fetched = commentAllJpaRepository.getOne(commentIdx);
        if (fetched == null) {
            return null;
        }

        if (dto.getDeliverySiteIdx() != null) {
            fetched.setDelivery_site_idx(dto.getDeliverySiteIdx());
        }
        if (dto.getStoreIdx() != null) {
            fetched.setStore_idx(dto.getStoreIdx());
        }
        if (dto.getTitle() != null) {
            fetched.setTitle(dto.getTitle());
        }
        if (dto.getIsAnonymous() != null) {
            fetched.setState_anonymous(dto.getIsAnonymous()?Byte.valueOf("1"):Byte.valueOf("0"));
        }
        if (dto.getCustomerIdx() != null) {
            fetched.setCustomer_idx(dto.getCustomerIdx());
        }
        if (dto.getContents() != null) {
            fetched.setContents(dto.getContents());
        }
        fetched.setModify_date(CustomTime.curTimestampSql());
        return commentAllJpaRepository.save(fetched);
    }

    @Override
    public Boolean delete(Integer commentIdx) {
        final CommentAll fetched = commentAllJpaRepository.getOne(commentIdx);
        if (fetched == null) {
            return false;
        } else {
            commentAllJpaRepository.delete(fetched);
            return true;
        }
    }
}
