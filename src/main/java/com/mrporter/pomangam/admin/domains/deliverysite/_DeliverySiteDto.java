package com.mrporter.pomangam.admin.domains.deliverysite;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class _DeliverySiteDto implements Serializable {

    private Long idx;

    private String title;

    private String location;

    private String regionCategory;

    private String campus;

    public _DeliverySiteDto(Long idx, String title, String location, String regionCategory, String campus) {
        this.idx = idx;
        this.title = title;
        this.location = location;
        this.regionCategory = regionCategory;
        this.campus = campus;
    }

    public DeliverySite toEntity() {
        return null;
    }
}