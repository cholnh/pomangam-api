package com.mrporter.pomangam.client.domains.employee;

import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeDto implements Serializable {

    private Long idx;
    private LocalDateTime registerDate;
    private LocalDateTime modifyDate;

    public Employee toEntity() {
        Employee entity = new ModelMapper().map(this, Employee.class);
        return entity;
    }

    public static EmployeeDto fromEntity(Employee entity) {
        if(entity == null) return null;
        EmployeeDto dto = new ModelMapper().map(entity, EmployeeDto.class);
        return dto;
    }

    public static List<EmployeeDto> fromEntities(List<Employee> entities) {
        if(entities == null) return null;
        List<EmployeeDto> dtos = new ArrayList<>();
        for(Employee entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }
}
