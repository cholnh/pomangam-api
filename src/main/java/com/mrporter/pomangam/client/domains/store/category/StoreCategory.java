package com.mrporter.pomangam.client.domains.store.category;

import com.mrporter.pomangam.client.domains.store.Store;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "store_category_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@ToString(exclude = "stores")
@Entity
public class StoreCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    /**
     * 음식점 카테고리 타이틀
     * ex) 한식, 중식, 양식 등
     */
    @NotBlank
    @Column(name = "category_title")
    private String categoryTitle;

    @OneToMany(mappedBy = "storeCategory", fetch = FetchType.LAZY)
    private List<Store> stores;

    @Builder
    public StoreCategory(@NotBlank String categoryTitle, List<Store> stores) {
        this.categoryTitle = categoryTitle;
        if(stores != null) {
            this.stores = stores;
        }
    }
}
