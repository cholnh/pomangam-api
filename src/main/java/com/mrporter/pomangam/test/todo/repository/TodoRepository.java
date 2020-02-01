package com.mrporter.pomangam.test.todo.repository;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.test.home.domain.HomeTbl;
import com.mrporter.pomangam.test.todo.domain.Todo;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = true)
public interface TodoRepository {
    List<Todo> get(PageRequest pageRequest);
    Todo get(int idx);
}
