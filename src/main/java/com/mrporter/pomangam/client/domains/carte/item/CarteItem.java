package com.mrporter.pomangam.client.domains.carte.item;

import com.mrporter.pomangam.client.domains._bases.EntityAuditing;
import com.mrporter.pomangam.client.domains.carte.CarteType;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "carte_item_tbl")
@DynamicUpdate
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CarteItem extends EntityAuditing {

    /**
     * 식단표 아이템 이름
     */
    @Column(name = "name", nullable = false, length = 40)
    private String name;

    /**
     * 식단표 아이템 타입
     */
    @Column(name = "carte_type", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private CarteType carteType;
}
