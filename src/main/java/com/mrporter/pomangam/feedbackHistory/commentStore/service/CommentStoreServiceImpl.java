package com.mrporter.pomangam.feedbackHistory.commentStore.service;

import com.mrporter.pomangam.common.security.user.domain.User;
import com.mrporter.pomangam.common.security.user.service.UserService;
import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStore;
import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreInputDto;
import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreViewDto;
import com.mrporter.pomangam.feedbackHistory.commentStore.repository.CommentStoreJpaRepository;
import com.mrporter.pomangam.feedbackHistory.commentStore.repository.CommentStoreRepositoryImpl;
import com.mrporter.pomangam.feedbackHistory.likeForCommentStore.repository.LikeForCommentStoreRepositoryImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.storeEntry.store.repository.StoreRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentStoreServiceImpl implements CommentStoreService {

    CommentStoreJpaRepository commentStoreJpaRepository;
    CommentStoreRepositoryImpl commentStoreRepository;
    UserService userService;
    LikeForCommentStoreRepositoryImpl likeForCommentStoreRepository;
    StoreRepositoryImpl storeRepository;

    @Override
    public List<CommentStoreViewDto> findByStoreIdx(Integer storeIdx, String orderBy, String customerId, PageRequest pageRequest) {
        if(pageRequest == null) {
            pageRequest = new PageRequest(0, 10);
        }
        User user = userService.findById(customerId);
        if(user == null) {
            return commentStoreRepository.findByStoreIdx(storeIdx, orderBy, null, pageRequest);
        } else {
            return commentStoreRepository.findByStoreIdx(storeIdx, orderBy, user.getIdx(), pageRequest);
        }
    }

    @Override
    public CommentStore saveCommentStoreInput(CommentStoreInputDto dto) {
        CommentStore comment = CommentStore.builder()
                .store_idx(dto.getStore_idx())
                .customer_idx(dto.getCustomer_idx())
                .cnt_star(dto.getCnt_star())
                .cnt_like(0)
                .contents(dto.getContents())
                .register_date(CustomTime.curTimestampSql())
                .state_active(Byte.valueOf("1"))
                .state_anonymous(dto.getState_anonymous())
                .build();
        CommentStore commentStore = commentStoreJpaRepository.save(comment);
        storeRepository.plusCommentCount(commentStore.getStore_idx());
        return commentStore;
    }

    @Override
    public void delete(Integer commentStoreIdx) {
        final Optional<CommentStore> optional = commentStoreJpaRepository.findById(commentStoreIdx);
        if(optional.isPresent()) {
            final CommentStore fetched = optional.get();
            storeRepository.minusCommentCount(fetched.getStore_idx());
            commentStoreJpaRepository.delete(fetched);
        }
    }

    @Override
    public void like(Integer commentStoreIdx, String customerId) {
        final User user = userService.findById(customerId);
        if(user != null) {
            likeForCommentStoreRepository.like(commentStoreIdx, user.getIdx());
        }
    }

    @Override
    public void unlike(Integer commentStoreIdx, String customerId) {
        final User user = userService.findById(customerId);
        if(user != null) {
            likeForCommentStoreRepository.unlike(commentStoreIdx, user.getIdx());
        }
    }
}
