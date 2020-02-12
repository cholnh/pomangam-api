package com.mrporter.pomangam.admin.domains.store;

import com.mrporter.pomangam.admin.domains.deliverysite._DeliverySiteDto;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySiteDto;
import com.mrporter.pomangam.client.domains.store.Store;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class _StoreDto implements Serializable {

    private Integer idx;

    private DeliverySiteDto deliverySite;

    private String title;

    private String storeCategory;

    private String description;

    private Float avgStar;

    private Integer cntLike;

    private Integer cntComment;

    public _StoreDto(Integer idx, DeliverySiteDto deliverySite, String title, String storeCategory, String description, Float avgStar, Integer cntLike, Integer cntComment) {
        this.idx = idx;
        this.deliverySite = deliverySite;
        this.title = title;
        this.storeCategory = storeCategory;
        this.description = description;
        this.avgStar = avgStar;
        this.cntLike = cntLike;
        this.cntComment = cntComment;
    }

    public static _StoreDto fromEntity(Store entity) {
        return new _StoreDto(
                entity.getIdx(),
                DeliverySiteDto.fromEntity(entity.getDeliverySite()),
                entity.getTitle(),
                entity.getStoreCategory().getCategoryTitle(),
                entity.getDescription(),
                entity.getAvgStar(),
                entity.getCntLike(),
                entity.getCntComment()
        );
    }

    public static List<_StoreDto> fromEntities(List<Store>entities) {
        List<_StoreDto> dtos = new ArrayList<>();
        for(Store entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }

}
