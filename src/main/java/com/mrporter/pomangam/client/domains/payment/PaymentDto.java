//package com.mrporter.pomangam.client.domains.payment;
//
//import lombok.*;
//import org.modelmapper.ModelMapper;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//public class PaymentDto implements Serializable {
//
//    private Long idx;
//    private LocalDateTime registerDate;
//    private LocalDateTime modifyDate;
//
//    public Payment toEntity() {
//        Payment entity = new ModelMapper().map(this, Payment.class);
//        return entity;
//    }
//
//    public static PaymentDto fromEntity(Payment entity) {
//        if(entity == null) return null;
//        PaymentDto dto = new ModelMapper().map(entity, PaymentDto.class);
//        return dto;
//    }
//
//    public static List<PaymentDto> fromEntities(List<Payment> entities) {
//        if(entities == null) return null;
//        List<PaymentDto> dtos = new ArrayList<>();
//        for(Payment entity : entities) {
//            dtos.add(fromEntity(entity));
//        }
//        return dtos;
//    }
//}
