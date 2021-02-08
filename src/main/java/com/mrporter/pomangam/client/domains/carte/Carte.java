package com.mrporter.pomangam.client.domains.carte;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.carte.image.CarteImage;
import com.mrporter.pomangam.client.domains.carte.item.CarteItem;
import com.mrporter.pomangam.client.domains.store.image.StoreImage;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "carte_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Carte extends EntityAuditing {

    /**
     * 날짜
     */
    @Column(name = "date", nullable = false)
    private LocalDate date;

    /**
     * 이미지 정보
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_carte", nullable = false)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequence ASC")
    private Set<CarteImage> images = new HashSet<>();

    /**
     * 식단표 아이템 리스트
     * 단방향 매핑
     */
    @JoinColumn(name = "idx_carte", nullable = false)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("idx ASC")
    private Set<CarteItem> carteItems = new HashSet<>();
}
