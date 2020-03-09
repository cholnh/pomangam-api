package com.mrporter.pomangam.client.domains.product.reply.like;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_reply_like_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ProductReplyLike extends EntityAuditing {

    /**
     * 유저 인덱스
     */
    @Column(name="idx_user", nullable = false)
    private Long idxUser;

    /**
     * 제품 댓글 인덱스
     */
    @Column(name="idx_product_reply", nullable = false)
    private Long idxProductReply;

    @Builder
    public ProductReplyLike(Long idxUser, Long idxProductReply) {
        this.idxUser = idxUser;
        this.idxProductReply = idxProductReply;
    }
}

