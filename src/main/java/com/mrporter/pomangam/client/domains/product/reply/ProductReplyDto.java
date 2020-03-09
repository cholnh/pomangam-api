package com.mrporter.pomangam.client.domains.product.reply;

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
public class ProductReplyDto implements Serializable {

    @JsonView(ProductReplyDtoView.CustomView.class)
    private Long idx;

    @JsonView(ProductReplyDtoView.CustomView.class)
    private LocalDateTime registerDate;

    @JsonView(ProductReplyDtoView.CustomView.class)
    private LocalDateTime modifyDate;

    private Long idxUser;   // write only

    @JsonView(ProductReplyDtoView.CustomView.class)
    private Long idxProduct;

    @JsonView(ProductReplyDtoView.CustomView.class)
    private Boolean isAnonymous;

    @JsonView(ProductReplyDtoView.CustomView.class)
    private String contents;

    @JsonView(ProductReplyDtoView.CustomView.class)
    private String nickname;    // user info (read only)

    @JsonView(ProductReplyDtoView.CustomView.class)
    private Boolean isOwn;      // 자신이 쓴 글인지 아닌지. (read only)

    @JsonView(ProductReplyDtoView.CustomView.class)
    private Integer cntLike;

    @JsonView(ProductReplyDtoView.CustomView.class)
    private Boolean isLike;

    public ProductReply toEntity() {
        ProductReply entity = new ModelMapper().map(this, ProductReply.class);
        return entity;
    }

    public static ProductReplyDto fromEntity(ProductReply entity) {
        if(entity == null) return null;
        ProductReplyDto dto = new ModelMapper().map(entity, ProductReplyDto.class);
        return dto;
    }

    public static List<ProductReplyDto> fromEntities(List<ProductReply> entities) {
        if(entities == null) return null;
        List<ProductReplyDto> dtos = new ArrayList<>();
        for(ProductReply entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
