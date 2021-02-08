package com.mrporter.pomangam.client.domains.carte;

import com.mrporter.pomangam.client.domains.carte.image.CarteImage;
import com.mrporter.pomangam.client.domains.carte.item.CarteItem;
import com.mrporter.pomangam.client.domains.carte.item.CarteItemDto;
import com.mrporter.pomangam.client.domains.store.image.StoreImage;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class CarteDto implements Serializable {
    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    private LocalDate date;
    private String mainImage;
    private String basicImage;
    private String standardImage;
    private String premiumImage;
    private List<CarteItemDto> carteItems;

    public Carte toEntity() {
        Carte entity = new ModelMapper().map(this, Carte.class);
        return entity;
    }

    public static CarteDto fromEntity(Carte entity) {
        CarteDto dto = null;
        if(entity != null) {
            dto = new ModelMapper().map(entity, CarteDto.class);
            dto.setCarteItems(convertCarteItems(entity.getCarteItems()));

            // images
            Set<CarteImage> carteImages = entity.getImages();
            if(carteImages != null && !carteImages.isEmpty()) {
                for(CarteImage carteImage : carteImages) {
                    switch (carteImage.getImageType()) {
                        case MAIN:
                            dto.setMainImage(carteImage.getImagePath()+"?v="+carteImage.getModifyDate());
                            break;
                        case BASIC:
                            dto.setBasicImage(carteImage.getImagePath()+"?v="+carteImage.getModifyDate());
                            break;
                        case STANDARD:
                            dto.setStandardImage(carteImage.getImagePath()+"?v="+carteImage.getModifyDate());
                            break;
                        case PREMIUM:
                            dto.setPremiumImage(carteImage.getImagePath()+"?v="+carteImage.getModifyDate());
                            break;
                    }
                }
            }
        }
        return dto;
    }

    public static List<CarteDto> fromEntities(List<Carte> entities) {
        List<CarteDto> dtos = new ArrayList<>();
        for(Carte entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }

    private static List<CarteItemDto> convertCarteItems(Set<CarteItem> carteItems) {
        List<CarteItemDto> carteItemDtos = new ArrayList<>();
        if(carteItems != null) {
            for(CarteItem carteItem : carteItems) {
                if(carteItem != null) {
                    carteItemDtos.add(CarteItemDto.fromEntity(carteItem));
                }
            }
        }
        return carteItemDtos;
    }
}
