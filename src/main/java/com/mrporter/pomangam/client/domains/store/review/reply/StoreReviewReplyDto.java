package com.mrporter.pomangam.client.domains.store.review.reply;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StoreReviewReplyDto implements Serializable {

    @JsonView(StoreReviewReplyDtoView.CustomView.class)
    private Long idx;

    @JsonView(StoreReviewReplyDtoView.CustomView.class)
    private LocalDateTime registerDate;

    @JsonView(StoreReviewReplyDtoView.CustomView.class)
    private LocalDateTime modifyDate;

    private Long idxUser;   // write only

    @JsonView(StoreReviewReplyDtoView.CustomView.class)
    private Long idxStoreReview;

    @JsonView(StoreReviewReplyDtoView.CustomView.class)
    private Boolean isAnonymous;

    @JsonView(StoreReviewReplyDtoView.CustomView.class)
    private String contents;

    @JsonView(StoreReviewReplyDtoView.CustomView.class)
    private String nickname;    // user info (read only)

    @JsonView(StoreReviewReplyDtoView.CustomView.class)
    private Boolean isOwn;      // 자신이 쓴 글인지 아닌지. (read only)

    public StoreReviewReply toEntity() {
        StoreReviewReply entity = new ModelMapper().map(this, StoreReviewReply.class);
        return entity;
    }

    public static StoreReviewReplyDto fromEntity(StoreReviewReply entity) {
        if(entity == null) return null;
        StoreReviewReplyDto dto = new ModelMapper().map(entity, StoreReviewReplyDto.class);
        return dto;
    }

    public static List<StoreReviewReplyDto> fromEntities(List<StoreReviewReply> entities) {
        if(entities == null) return null;
        List<StoreReviewReplyDto> dtos = new ArrayList<>();
        for(StoreReviewReply entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
