package com.mrporter.pomangam.client.domains.vbank;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class VBankDepositDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private Integer input;
    private String bank;
    private Integer remain;
    private String name;
    private String transferDate;
    private String content;
    private VBankStatus status;
    private Long idxOrder;

    @Builder
    public VBankDepositDto(Long idx, LocalDateTime registerDate, Integer input, String bank, Integer remain, String name, String transferDate, String content, VBankStatus status, Long idxOrder) {
        this.idx = idx;
        this.registerDate = registerDate;
        this.input = input;
        this.bank = bank;
        this.remain = remain;
        this.name = name;
        this.transferDate = transferDate;
        this.content = content;
        this.status = status;
        this.idxOrder = idxOrder;
    }

    public VBankDeposit toEntity() {
        VBankDeposit entity = new ModelMapper().map(this, VBankDeposit.class);
        return entity;
    }

    public static VBankDepositDto fromEntity(VBankDeposit entity) {
        if(entity == null) return null;
        VBankDepositDto dto = new ModelMapper().map(entity, VBankDepositDto.class);
        return dto;
    }

    public static List<VBankDepositDto> fromEntities(List<VBankDeposit> entities) {
        if(entities == null) return null;
        List<VBankDepositDto> dtos = new ArrayList<>();
        for(VBankDeposit entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }

    public boolean equals(VBankDepositDto another) {
        return this.transferDate.equals(another.getTransferDate()) &&
                this.name.equals(another.getName()) &&
                this.input.intValue() == another.getInput().intValue() &&
                this.remain.intValue() == another.getRemain().intValue();
    }
}
