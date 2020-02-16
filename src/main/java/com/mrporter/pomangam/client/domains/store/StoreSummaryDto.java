package com.mrporter.pomangam.client.domains.store;

import com.mrporter.pomangam.client.domains.store.image.StoreImage;
import com.mrporter.pomangam.client.domains.store.image.StoreImageType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StoreSummaryDto implements Serializable {

    private Long idx;
    private String name;
    private String description;
    private String subDescription;
    private Float avgStar;
    private Integer cntLike;
    private Integer cntComment;
    private Integer sequence;

    // 추가 사항
    private String brandImagePath;
    private String storeImageMainPath;
    private List<String> storeImageSubPaths = new ArrayList<>();
    private Integer promotionType;
    private Integer promotionValue; // promotionType: 0 -> 할인안함 / 1 -> 할인가(단위: 원) / 2 -> 할인률(단위: %)
    private Integer couponType;
    private Integer couponValue;

    public static StoreSummaryDto fromEntity(Store entity) {
        StoreSummaryDto dto = new ModelMapper().map(entity, StoreSummaryDto.class);

        List<StoreImage> storeImages = entity.getImages();
        if(storeImages != null && !storeImages.isEmpty()) {
            for(StoreImage storeImage : storeImages) {
                switch (storeImage.getImageType()) {
                    case MAIN:
                        dto.setStoreImageMainPath(storeImage.getImagePath());
                        break;
                    case SUB:
                        dto.getStoreImageSubPaths().add(storeImage.getImagePath());
                        break;
                    case BRAND:
                        dto.setBrandImagePath(storeImage.getImagePath());
                        break;
                }
            }
        }

        dto.setPromotionType(0);
        dto.setPromotionValue(0);
        dto.setCouponType(0);
        dto.setCouponValue(0);

        return dto;
    }

    public static List<StoreSummaryDto> fromEntities(List<Store> entities) {
        List<StoreSummaryDto> dtos = new ArrayList<>();
        for(Store entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
