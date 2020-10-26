package com.mrporter.pomangam.client.domains.deliverysite.detail;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DeliveryDetailSiteDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;
    private Long idxDeliverySite;
    private String name;
    private String location;
    private Integer sequence;
    private LocalTime additionalTime;
    private Double latitude;
    private Double longitude;
    private String abbreviation;

    public DeliveryDetailSite toEntity() {
        DeliveryDetailSite entity = new ModelMapper().map(this, DeliveryDetailSite.class);
        DeliverySite deliverySite = DeliverySite.builder().build();
        deliverySite.setIdx(entity.getDeliverySite().getIdx());
        entity.setDeliverySite(deliverySite);
        return entity;
    }

    public static DeliveryDetailSiteDto fromEntity(DeliveryDetailSite entity) {
        DeliveryDetailSiteDto dto = new ModelMapper().map(entity, DeliveryDetailSiteDto.class);
        if(entity.getDeliverySite() != null) {
            dto.setIdxDeliverySite(entity.getDeliverySite().getIdx());
        }
        return dto;
    }

    public static List<DeliveryDetailSiteDto> fromEntities(List<DeliveryDetailSite> entities) {
        List<DeliveryDetailSiteDto> dtos = new ArrayList<>();
        for(DeliveryDetailSite entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}