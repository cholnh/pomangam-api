package com.mrporter.pomangam.feedbackHistory.commentStore.service;

import com.mrporter.pomangam.feedbackHistory.commentStore.domain.CommentStoreViewDto;
import com.mrporter.pomangam.feedbackHistory.commentStore.repository.CommentStoreRepositoryImpl;
import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentStoreServiceImpl implements CommentStoreService {

    CommentStoreRepositoryImpl commentStoreRepository;

    @Override
    public List<CommentStoreViewDto> findByStoreIdx(Integer storeIdx, String orderBy, PageRequest pageRequest) {
        if(pageRequest == null) {
            pageRequest = new PageRequest(0, 10);
        }
        return commentStoreRepository.findByStoreIdx(storeIdx, orderBy, pageRequest);
    }
}
