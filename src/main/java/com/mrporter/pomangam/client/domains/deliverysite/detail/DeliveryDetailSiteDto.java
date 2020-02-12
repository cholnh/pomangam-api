package com.mrporter.pomangam.client.domains.deliverysite.detail;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import com.mrporter.pomangam.client.domains.deliverysite.DeliverySiteDto;
import com.mrporter.pomangam.client.domains.user.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class DeliveryDetailSiteDto implements Serializable {

    private Integer idx;

    private DeliverySiteDto deliverySite;

    private String title;

    private String location;

    private Integer sequence;

    private LocalTime additionalTime;

    private Double latitude;

    private Double longitude;

    private String abbreviation;

    public DeliveryDetailSite toEntity() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, DeliveryDetailSite.class);
    }

    public static DeliveryDetailSiteDto fromEntity(DeliveryDetailSite entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, DeliveryDetailSiteDto.class);
    }

    public static List<DeliveryDetailSiteDto> fromEntities(List<DeliveryDetailSite> entities) {
        List<DeliveryDetailSiteDto> dtos = new ArrayList<>();
        for(DeliveryDetailSite entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}