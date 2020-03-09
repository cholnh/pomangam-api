package com.mrporter.pomangam.client.domains.store.review;

import com.fasterxml.jackson.annotation.JsonView;
import com.mrporter.pomangam.client.domains.product.reply.ProductReplyDtoView;
import com.mrporter.pomangam.client.domains.store.review.image.StoreReviewImage;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StoreReviewDto implements Serializable {

    @JsonView(StoreReviewDtoView.CustomView.class)
    private Long idx;

    @JsonView(StoreReviewDtoView.CustomView.class)
    private LocalDateTime registerDate;

    @JsonView(StoreReviewDtoView.CustomView.class)
    private LocalDateTime modifyDate;

    private Long idxUser; // write only

    @JsonView(StoreReviewDtoView.CustomView.class)
    private Long idxStore;

    @JsonView(StoreReviewDtoView.CustomView.class)
    private Boolean isAnonymous;

    @JsonView(StoreReviewDtoView.CustomView.class)
    private String title;

    @JsonView(StoreReviewDtoView.CustomView.class)
    private String contents;

    @JsonView(StoreReviewDtoView.CustomView.class)
    private Float star;

    @JsonView(StoreReviewDtoView.CustomView.class)
    private Integer cntLike;

    @JsonView(StoreReviewDtoView.CustomView.class)
    private String nickname;    // user info (read only)

    @JsonView(StoreReviewDtoView.CustomView.class)
    private Boolean isOwn;      // 자신이 쓴 글인지 아닌지. (read only)

    @JsonView(StoreReviewDtoView.CustomView.class)
    private Boolean isLike;

    private Long idxDeliverySite;

    @JsonView(StoreReviewDtoView.CustomView.class)
    private String storeReviewImageMainPath;

    @JsonView(StoreReviewDtoView.CustomView.class)
    private List<String> storeReviewImageSubPaths = new ArrayList<>();

    private Boolean isImageUpdated; // patch 시, 이미지가 수정되는지 유무.

    public StoreReview toEntity() {
        StoreReview entity = new ModelMapper().map(this, StoreReview.class);
        return entity;
    }

    public static StoreReviewDto fromEntity(StoreReview entity) {
        if(entity == null) return null;
        StoreReviewDto dto = new ModelMapper().map(entity, StoreReviewDto.class);

        List<StoreReviewImage> storeReviewImages = entity.getImages();
        if(storeReviewImages != null && !storeReviewImages.isEmpty()) {
            for(StoreReviewImage storeReviewImage : storeReviewImages) {
                switch (storeReviewImage.getImageType()) {
                    case MAIN:
                        dto.setStoreReviewImageMainPath(storeReviewImage.getImagePath());
                        break;
                    case SUB:
                        dto.getStoreReviewImageSubPaths().add(storeReviewImage.getImagePath());
                        break;
                }
            }
        }

        dto.setIdxUser(null);   // always null

        return dto;
    }

    public static List<StoreReviewDto> fromEntities(List<StoreReview> entities) {
        if(entities == null) return null;
        List<StoreReviewDto> dtos = new ArrayList<>();
        for(StoreReview entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}