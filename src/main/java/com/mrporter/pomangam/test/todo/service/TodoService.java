package com.mrporter.pomangam.test.todo.service;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.test.todo.domain.TodoDto;

import java.util.List;

public interface TodoService {
    int count();
    List<TodoDto> get(PageRequest pageRequest);
    TodoDto get(int idx);
    TodoDto post(TodoDto bean);
    void delete(int idx);
    TodoDto put(TodoDto bean);
    TodoDto patch(TodoDto bean);
}
