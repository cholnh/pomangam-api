package com.mrporter.pomangam.client.domains.deliverysite;

import com.mrporter.pomangam.client.domains.deliverysite.region.RegionDto;
import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.StoreDto;
import com.mrporter.pomangam.client.domains.user.User;
import com.mrporter.pomangam.client.domains.user.UserDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class DeliverySiteDto implements Serializable {

    private Integer idx;

    private String title;

    private String location;

    private String campus;

    private RegionDto _region;

    public DeliverySite toEntity() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, DeliverySite.class);
    }

    public static DeliverySiteDto fromEntity(DeliverySite entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, DeliverySiteDto.class);
    }

    public static List<DeliverySiteDto> fromEntities(List<DeliverySite> entities) {
        List<DeliverySiteDto> dtos = new ArrayList<>();
        for(DeliverySite entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}