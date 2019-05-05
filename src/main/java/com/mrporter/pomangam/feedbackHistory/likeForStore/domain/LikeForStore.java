package com.mrporter.pomangam.feedbackHistory.likeForStore.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "like_for_store_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@IdClass(LikeForStoreKey.class)
public class LikeForStore implements Serializable {

    @Id
    private Integer store_idx;

    @Id
    private Integer customer_idx;

    private Byte type;

    @Builder
    public LikeForStore(Integer store_idx, Integer customer_idx, Byte type) {
        this.store_idx = store_idx;
        this.customer_idx = customer_idx;
        this.type = type;
    }
}
