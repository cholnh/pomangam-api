package com.mrporter.pomangam.client.domains.store.category;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.store.Store;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "store_category_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "stores")
public class StoreCategory extends EntityAuditing {

    /**
     * 음식점 카테고리 타이틀
     * ex) 한식, 중식, 양식 등
     */
    @Column(name = "category_title", nullable = false)
    private String categoryTitle;

    @OneToMany(mappedBy = "storeCategory", fetch = FetchType.LAZY)
    @OrderBy("sequence ASC")
    private List<Store> stores;

    @Builder
    public StoreCategory(String categoryTitle, List<Store> stores) {
        this.categoryTitle = categoryTitle;
        if(stores != null) {
            this.stores = stores;
        }
    }
}
