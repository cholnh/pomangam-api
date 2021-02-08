package com.mrporter.pomangam.client.repositories.store;

import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.StoreDto;
import com.mrporter.pomangam.client.domains.store.category.StoreCategory;
import com.mrporter.pomangam.client.domains.store.image.StoreImage;
import com.mrporter.pomangam.client.domains.store.image.StoreImageType;
import com.mrporter.pomangam.client.domains.store.info.ProductionInfo;
import com.mrporter.pomangam.client.domains.store.info.StoreInfo;
import com.mrporter.pomangam.client.domains.store.schedule.StoreSchedule;
import com.mrporter.pomangam.client.repositories.store.category.StoreCategoryJpaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@RunWith(SpringRunner.class)
public class StoreJpaRepositoryTest {
    @Autowired
    private StoreJpaRepository storeJpaRepository;

    @Autowired
    private StoreCategoryJpaRepository storeCategoryJpaRepository;

    @Test
    public void 업체_저장_테스트() {
        // Given
        StoreCategory category = storeCategoryJpaRepository.save(makeStoreCategoryDummy());
        Store input = makeStoreDummy(category);

        // When
        Store saved = storeJpaRepository.save(input);

        // Then
        Store output = storeJpaRepository
                .findById(saved.getIdx())
                .orElseThrow(RuntimeException::new);
        System.out.println(StoreDto.fromEntity(output));
        Assert.assertEquals(input.getStoreInfo().getName(), output.getStoreInfo().getName());
    }

    @Test
    public void 업체_이미지_저장_테스트() {
        // Given
        StoreCategory category = storeCategoryJpaRepository.save(makeStoreCategoryDummy());
        Store input = makeStoreDummy(category);
        StoreImage storeBrandImage = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/1/brand.png")
                .imageType(StoreImageType.BRAND)
                .build();
        StoreImage storeImage1_1 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/1/1.jpg")
                .imageType(StoreImageType.MAIN)
                .sequence(1)
                .build();
        StoreImage storeImage1_2 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/2/1.jpg")
                .imageType(StoreImageType.MAIN)
                .sequence(1)
                .build();
        StoreImage storeImage1_3 = StoreImage.builder()
                .imagePath("/assets/images/dsites/1/stores/2/2.jpg")
                .imageType(StoreImageType.SUB)
                .sequence(2)
                .build();

        // When
        input.addImages(storeBrandImage, storeImage1_1, storeImage1_2, storeImage1_3);
        Store saved = storeJpaRepository.save(input);

        // Then
        Store output = storeJpaRepository
                .findById(saved.getIdx())
                .orElseThrow(RuntimeException::new);
        System.out.println(StoreDto.fromEntity(output));
        Assert.assertEquals(input.getStoreInfo().getName(), output.getStoreInfo().getName());
    }

    private StoreCategory makeStoreCategoryDummy() {
        return StoreCategory.builder()
                .categoryTitle("한식")
                .build();
    }
    private Store makeStoreDummy(StoreCategory storeCategory) {
        return Store.builder()
                .storeCategory(storeCategory)
                .storeInfo(StoreInfo.builder()
                        .name("음식점 이름")
                        .description("음식점 설명")
                        .subDescription("음식점 부가설명")
                        .ownerName("업주 이름")
                        .companyName("업체 상호명")
                        .companyLocation("업체 위치")
                        .companyPhoneNumber("02-123-1234")
                        .build())
                .productionInfo(ProductionInfo.builder()
                        .minimumTime((short) 1)         // 1분에
                        .parallelProduction((short) 2)  // 2개 제작
                        .maximumProduction((short) 20)  // 최대 20개 까지
                        .build())
                .storeSchedule(StoreSchedule.builder()
                        .openTime(LocalTime.parse("08:00:00"))  // 오전 8시 시작
                        .closeTime(LocalTime.parse("21:00:00")) // 오후 9시 종료
                        .isOpening(true)                        // 영업 일시정지 여부
                        .pauseDescription("정지 사유")
                        .build())
                .build();
    }
}
