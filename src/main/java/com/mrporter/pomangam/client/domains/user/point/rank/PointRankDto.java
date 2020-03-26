package com.mrporter.pomangam.client.domains.user.point.rank;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PointRankDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private Short level;
    private String title;
    private Integer nextLowerLimitOrderCount;
    private Integer nextLowerLimitRecommendCount;
    private Integer rewardCouponPrice;
    private Integer percentSavePoint;
    private Integer priceSavePoint;

    private Integer userOrderCount;
    private Integer userRecommendCount;

    public PointRank toEntity() {
        PointRank entity = new ModelMapper().map(this, PointRank.class);
        return entity;
    }

    public static PointRankDto fromEntity(PointRank entity) {
        if(entity == null) return null;
        PointRankDto dto = new ModelMapper().map(entity, PointRankDto.class);
        return dto;
    }

    public static List<PointRankDto> fromEntities(List<PointRank> entities) {
        if(entities == null) return null;
        List<PointRankDto> dtos = new ArrayList<>();
        for(PointRank entity : entities) {
            PointRankDto dto = fromEntity(entity);
            if(dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
