package com.mrporter.pomangam.client.domains.store.like;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "store_like_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class StoreLike extends EntityAuditing {

    /**
     * 유저 인덱스
     */
    @Column(name="idx_user", nullable = false)
    private Long idxUser;

    /**
     * 업체 인덱스
     */
    @Column(name="idx_store", nullable = false)
    private Long idxStore;

    @Builder
    public StoreLike(Long idxUser, Long idxStore) {
        this.idxUser = idxUser;
        this.idxStore = idxStore;
    }
}

