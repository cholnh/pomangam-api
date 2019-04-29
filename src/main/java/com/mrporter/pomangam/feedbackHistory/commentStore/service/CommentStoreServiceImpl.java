package com.mrporter.pomangam.feedbackHistory.commentStore.service;

import com.mrporter.pomangam.common.util.time.CustomTime;
import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStore;
import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreInputDto;
import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreViewDto;
import com.mrporter.pomangam.feedbackHistory.commentStore.repository.CommentStoreJpaRepository;
import com.mrporter.pomangam.feedbackHistory.commentStore.repository.CommentStoreRepository;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentStoreServiceImpl implements CommentStoreService {

    CommentStoreJpaRepository commentStoreJpaRepository;
    CommentStoreRepository commentStoreRepository;

    @Override
    public List<CommentStoreViewDto> findByStoreIdx(Integer storeIdx, String orderBy, PageRequest pageRequest) {
        if(pageRequest == null) {
            pageRequest = new PageRequest(0, 10);
        }
        return commentStoreRepository.findByStoreIdx(storeIdx, orderBy, pageRequest);
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
        return commentStoreJpaRepository.save(comment);
    }
}
