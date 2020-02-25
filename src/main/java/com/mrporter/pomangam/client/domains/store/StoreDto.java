package com.mrporter.pomangam.client.domains.store;

import com.mrporter.pomangam.client.domains.store.image.StoreImage;
import com.mrporter.pomangam.client.domains.store.info.ProductionInfo;
import com.mrporter.pomangam.client.domains.store.info.StoreInfo;
import com.mrporter.pomangam.client.domains.store.schedule.StoreSchedule;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StoreDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private Long idxDeliverySite;
    private String storeCategory;
    private StoreInfo storeInfo;
    private ProductionInfo productionInfo;
    private StoreSchedule storeSchedule;
    private Float avgStar;
    private Integer cntLike;
    private Integer cntComment;
    private Integer sequence;

    // images
    private String brandImagePath;
    private String storeImageMainPath;
    private List<String> storeImageSubPaths = new ArrayList<>();

    public Store toEntity() {
        Store entity = new ModelMapper().map(this, Store.class);
        return entity;
    }

    public static StoreDto fromEntity(Store entity) {
        if(entity == null) return null;
        StoreDto dto = new ModelMapper().map(entity, StoreDto.class);

        // images
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

        // category
        dto.setStoreCategory(entity.getStoreCategory().getCategoryTitle());

        return dto;
    }

    public static List<StoreDto> fromEntities(List<Store> entities) {
        if(entities == null) return null;
        List<StoreDto> dtos = new ArrayList<>();
        for(Store entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
