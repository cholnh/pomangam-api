package com.mrporter.pomangam.test.data.storeReview;

import com.mrporter.pomangam.client.domains.store.review.StoreReview;
import com.mrporter.pomangam.client.domains.store.review.image.StoreReviewImage;
import com.mrporter.pomangam.client.domains.store.review.image.StoreReviewImageType;
import com.mrporter.pomangam.client.repositories.store.review.StoreReviewJpaRepository;
import com.mrporter.pomangam.client.services._bases.ImagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class StoreReviewData {
    @Autowired
    StoreReviewJpaRepository storeReviewJpaRepository;

    @Transactional
    public void of(Long idx, Long uIdx, Long dIdx, Long sIdx, String title, String contents, Float star, String productName, String ownerReply, LocalDateTime ownerReplyModifyDate, boolean isAnonymous, String...images) {
        StoreReview review = StoreReview.builder()
            .idx(idx)
            .idxUser(uIdx)
            .idxStore(sIdx)
            .title(title)
            .contents(contents)
            .star(star)
            .isAnonymous(isAnonymous)
            .productName(productName)
            .ownerReply(ownerReply)
            .ownerReplyModifyDate(ownerReplyModifyDate)
            .build();

        // image
        if(images != null) {
            for(int i=0; i<images.length; i++) {
                StoreReviewImage image = StoreReviewImage.builder()
                    .imagePath(ImagePath.reviews(dIdx, sIdx, idx) + (Long.parseLong(images[i])) + ".jpg")
                    .imageType(i==0 ? StoreReviewImageType.MAIN : StoreReviewImageType.SUB)
                    .sequence(i+1)
                    .build();
                review.addImage(image);
            }
        }

        storeReviewJpaRepository.save(review);
    }
}


