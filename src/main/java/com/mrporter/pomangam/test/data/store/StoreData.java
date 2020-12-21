package com.mrporter.pomangam.test.data.store;

import com.mrporter.pomangam.client.domains.product.category.ProductCategory;
import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.category.StoreCategory;
import com.mrporter.pomangam.client.domains.store.image.StoreImage;
import com.mrporter.pomangam.client.domains.store.image.StoreImageType;
import com.mrporter.pomangam.client.domains.store.info.ProductionInfo;
import com.mrporter.pomangam.client.domains.store.info.StoreInfo;
import com.mrporter.pomangam.client.domains.store.schedule.StoreSchedule;
import com.mrporter.pomangam.client.domains.store.story.StoreStory;
import com.mrporter.pomangam.client.domains.store.story.image.StoreStoryImage;
import com.mrporter.pomangam.client.repositories.product.category.ProductCategoryJpaRepository;
import com.mrporter.pomangam.client.repositories.store.StoreJpaRepository;
import com.mrporter.pomangam.client.repositories.store.category.StoreCategoryJpaRepository;
import com.mrporter.pomangam.client.services._bases.ImagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Component
public class StoreData {

    @Autowired
    StoreJpaRepository storeJpaRepository;


    @Transactional
    public void of(Long idx, Long dIdx, Long scIdx, String name, String desc, String sub, float avgStar, int cntLike, int cntComment, int cntOrder, int sequence, List<Integer> imagePaths, List<String> stories, List<String> productCategories) {
        Store store = Store.builder()
                .idx(idx)
                .storeInfo(StoreInfo.builder()
                        .name(name)
                        .description(desc)
                        .subDescription(sub)
                        .build())
                .productionInfo(ProductionInfo.builder()
                        .minimumTime((short) 1)
                        .parallelProduction((short) 1)
                        .maximumProduction((short) 20)
                        .build())
                .storeSchedule(StoreSchedule.builder()
                        .openTime(LocalTime.parse("09:00:00"))
                        .closeTime(LocalTime.parse("21:00:00"))
                        .isOpening(true)
                        .pauseDescription("임시휴무")
                        .build())
                .avgStar(avgStar)
                .cntLike(cntLike)
                .cntReview(cntComment)
                .cntOrder(cntOrder)
                .storeCategory(StoreCategory.builder().idx(scIdx).build())
                .sequence(sequence)
                .build();

        // image
        StoreImage storeBrandImage = StoreImage.builder()
                .imagePath(ImagePath.stores(idx) + "brand.png")
                .imageType(StoreImageType.BRAND)
                .build();
        store.addImages(storeBrandImage);
        for(int i=0; i<imagePaths.size(); i++) {
            StoreImage storeImage = StoreImage.builder()
                    .imagePath(ImagePath.stores(idx) + (imagePaths.get(i)) + ".jpg")
                    .imageType(i==0 ? StoreImageType.MAIN : StoreImageType.SUB)
                    .sequence(i+1)
                    .build();
            store.addImages(storeImage);
        }

        // store's story
        for(int i=0; i<stories.size(); i++) {
            StoreStory storeStory = StoreStory.builder()
                    .title(stories.get(i))
                    .build();
            storeStory.addImages(
                    StoreStoryImage.builder()
                            .imagePath(ImagePath.story(idx, 1L)+"1.jpg")
                            .sequence(1)
                            .build(),
                    StoreStoryImage.builder()
                            .imagePath(ImagePath.story(idx, 1L)+"2.jpg")
                            .sequence(2)
                            .build());
            store.addStories(storeStory);
        }

        // store's product category
        for(int i=0; i<productCategories.size(); i++) {
            ProductCategory productCategory = ProductCategory.builder()
                    .categoryTitle(productCategories.get(i))
                    .build();
            store.addProductCategories(productCategory);
        }

        storeJpaRepository.save(store);
    }
}