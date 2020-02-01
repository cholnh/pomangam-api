package com.mrporter.pomangam.test.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    Integer idx;
    String username;
    String title;
    String contents;
    Boolean completed;

    public static List<TodoDto> fromEntities(List<Todo> entities) {
        if(entities == null) return null;
        List<TodoDto> dtos = new ArrayList<>();
        for(Todo entity : entities) {
            dtos.add(fromEntity(entity));
        }
        return dtos;
    }

    public static TodoDto fromEntity(Todo entity) {
        return entity == null
            ? null
            : new TodoDto(
                entity.idx,
                entity.username,
                entity.title,
                entity.contents,
                entity.completed.compareTo(Byte.valueOf("1")) == 0);
    }

    public Todo toEntity() {
        return Todo.builder()
                .username(username)
                .title(title)
                .contents(contents)
                .completed(completed ? Byte.valueOf("1") : Byte.valueOf("0"))
                .build();
    }
}
