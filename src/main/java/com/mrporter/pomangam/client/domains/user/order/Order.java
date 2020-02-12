package com.mrporter.pomangam.client.domains.user.order;

import com.mrporter.pomangam.client.domains.store.Store;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Table(name = "order_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @NotBlank
    private String categoryTitle;

    @OneToMany(mappedBy = "storeCategory", fetch = FetchType.LAZY)
    private List<Store> stores;

    @Builder
    public Order(@NotBlank String categoryTitle, List<Store> stores) {
        this.categoryTitle = categoryTitle;
        if(stores != null) {
            this.stores = stores;
        }
    }
}
