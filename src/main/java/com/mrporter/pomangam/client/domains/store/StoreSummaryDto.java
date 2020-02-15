package com.mrporter.pomangam.client.domains.store;

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
    private String title;
    private String description;
    private String subDescription;
    private Float avgStar;
    private Integer cntLike;
    private Integer cntComment;

    // 추가 사항
    private List<String> storeImagePaths;
    private String brandImagePath;
    private Integer promotionType;
    private Integer promotionValue; // promotionType: 0 -> 할인안함 / 1 -> 할인가(단위: 원) / 2 -> 할인률(단위: %)
    private Integer couponType;
    private Integer couponValue;

    public static StoreSummaryDto fromEntity(Store entity) {
        ModelMapper modelMapper = new ModelMapper();
        StoreSummaryDto dto = modelMapper.map(entity, StoreSummaryDto.class);

        List<String> imagePaths = new ArrayList<>();
        imagePaths.add("/assets/image/store/1.jpg");
        imagePaths.add("/assets/image/store/2.jpg");
        imagePaths.add("/assets/image/store/3.jpg");
        dto.setStoreImagePaths(imagePaths);
        dto.setBrandImagePath("/assets/image/store/1.png");
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
