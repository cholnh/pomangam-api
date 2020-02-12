package com.mrporter.pomangam.client.domains.deliverysite.region;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Table(name = "region_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@ToString(exclude = "deliverySites")
@DynamicInsert
@DynamicUpdate
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    /**
     * 지역명
     */
    @NotBlank
    private String title;

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<DeliverySite> deliverySites;

    @Builder
    public Region(@NotBlank String title, List<DeliverySite> deliverySites) {
        this.title = title;
        if(deliverySites != null) {
            this.deliverySites = deliverySites;
        }
    }
}
