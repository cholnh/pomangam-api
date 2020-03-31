package com.mrporter.pomangam.client.domains.user.coupon;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CouponDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private Boolean isUsed;
    private Integer discountCost;
    private String title;
    private String code;
    private Long idxUser;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;

    public Coupon toEntity() {
        Coupon entity = new ModelMapper().map(this, Coupon.class);
        return entity;
    }

    public static CouponDto fromEntity(Coupon entity) {
        if(entity == null) return null;
        CouponDto dto = new ModelMapper().map(entity, CouponDto.class);

        dto.setIdxUser(entity.getUser() == null ? null : entity.getUser().getIdx());

        return dto;
    }

    public static List<CouponDto> fromEntities(List<Coupon> entities) {
        if(entities == null) return null;
        List<CouponDto> dtos = new ArrayList<>();
        for(Coupon entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
