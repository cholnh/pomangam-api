package com.mrporter.pomangam.test.data.storeReviewReply;

import com.mrporter.pomangam.client.domains.store.review.reply.StoreReviewReplyDto;
import com.mrporter.pomangam.client.services.store.review.reply.StoreReviewReplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StoreReviewReplyData {

    @Autowired
    StoreReviewReplyServiceImpl storeReviewReplyService;

    @Transactional
    public void of(Long uIdx, Long rIdx, String contents, boolean isAnonymous) {
        StoreReviewReplyDto reply = new StoreReviewReplyDto();
        reply.setIdxUser(uIdx);
        reply.setIdxStoreReview(rIdx);
        reply.setContents(contents);
        reply.setIsAnonymous(isAnonymous);

        storeReviewReplyService.save(reply);
    }
}
