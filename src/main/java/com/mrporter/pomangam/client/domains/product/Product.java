package com.mrporter.pomangam.client.domains.product;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.product.category.ProductCategory;
import com.mrporter.pomangam.client.domains.product.cost.Cost;
import com.mrporter.pomangam.client.domains.product.image.ProductImage;
import com.mrporter.pomangam.client.domains.product.info.ProductInfo;
import com.mrporter.pomangam.client.domains.product.sub.ProductSubMapper;
import com.mrporter.pomangam.client.domains.product.sub.category.ProductSubCategory;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"productCategory", "images", "subs"})
public class Product extends EntityAuditing {

    /**
     * 업체 인덱스
     * cf. (업체와 제품은 서로)에그리거트 단위를 벗어남 -> 불필요한 객체탐색이 포함됨 -> 연관관계 끊음
     */
    @Column(name = "idx_store", nullable = false)
    private Long idxStore;

    /**
     * 가격
     */
    @Embedded
    private Cost cost;

    /**
     * 제품 정보
     */
    @Embedded
    private ProductInfo productInfo;

    /**
     * 제품 분류
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idx_product_category")
    private ProductCategory productCategory;

    /**
     * 총 좋아요 개수
     */
    @Column(name = "cnt_like", nullable = false, columnDefinition = "INT default 0")
    private Integer cntLike;

    /**
     * 총 댓글 개수
     */
    @Column(name = "cnt_reply", nullable = false, columnDefinition = "INT default 0")
    private Integer cntReply;

    /**
     * 순서
     */
    @Column(name = "sequence", nullable = false, columnDefinition = "INT default 0")
    private Integer sequence;

    /**
     * 이미지 정보
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_product", nullable = false)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequence ASC")
    private List<ProductImage> images = new ArrayList<>();

    /**
     * 서브 제품 Mapper
     */
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @OrderBy("sequence ASC")
    private List<ProductSubMapper> subs = new ArrayList<>();

    @PrePersist
    private void prePersist() {
        this.cntLike = 0; // always 0 when its insert
        this.cntReply = 0;
        this.sequence = sequence == null ? 0 : sequence;
    }

    @Builder
    public Product(Long idx, Long idxStore, Cost cost, ProductInfo productInfo, ProductCategory productCategory, Integer cntLike, Integer cntReply, Integer sequence, List<ProductImage> images, List<ProductSubMapper> subs) {
        super.setIdx(idx);
        this.idxStore = idxStore;
        this.cost = cost;
        this.productInfo = productInfo;
        this.productCategory = productCategory;
        this.cntLike = cntLike;
        this.cntReply = cntReply;
        this.sequence = sequence;
        this.images = images;
        this.subs = subs;
    }

    public void addImage(ProductImage productImage) {
        if(this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(productImage);
    }
    public void addCntLike() {
        if(this.cntLike == null) {
            this.cntLike = 0;
        }
        this.cntLike++;
    }
    public void addCntReply() {
        if(this.cntReply == null) {
            this.cntReply = 0;
        }
        this.cntReply++;
    }

    public void subCntLike() {
        if(this.cntLike == null) {
            this.cntLike = 0;
        }
        this.cntLike--;
        if(this.cntLike < 0) {
            this.cntLike = 0;
        }
    }
    public void subCntReply() {
        if(this.cntReply == null) {
            this.cntReply = 0;
        }
        this.cntReply--;
        if(this.cntReply < 0) {
            this.cntReply = 0;
        }
    }
}
