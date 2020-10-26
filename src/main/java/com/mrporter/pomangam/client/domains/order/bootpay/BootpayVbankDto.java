package com.mrporter.pomangam.client.domains.order.bootpay;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BootpayVbankDto implements Serializable {

    private Long idxOrder;
    private String vbankName;
    private String vbankAccount;
    private Integer vbankPrice;

    public static BootpayVbankDto fromEntity(BootpayVbank entity) {
        if(entity == null) return null;
        BootpayVbankDto dto = new BootpayVbankDto();

        dto.setIdxOrder(entity.getIdxOrder());
        dto.setVbankName(entity.getVbankName());
        dto.setVbankAccount(entity.getVbankAccount());
        dto.setVbankPrice(entity.getVbankPrice());

        return dto;
    }

    public BootpayVbank toEntity() {
        BootpayVbank entity = BootpayVbank.builder()
                .idxOrder(idxOrder)
                .vbankName(vbankName)
                .vbankAccount(vbankAccount)
                .vbankPrice(vbankPrice)
                .build();
        return entity;
    }
}
