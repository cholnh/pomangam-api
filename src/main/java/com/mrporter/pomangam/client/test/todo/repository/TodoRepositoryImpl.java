package com.mrporter.pomangam.client.test.todo.repository;

import com.mrporter.pomangam.client.test.todo.domain.PageRequest;
import com.mrporter.pomangam.client.test.todo.domain.Todo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class TodoRepositoryImpl implements TodoRepository {

    RepositoryHelper _repositoryHelper;

    @Override
    public List<Todo> get(PageRequest pageRequest) {
        return _repositoryHelper.query("SELECT * FROM todo_tbl", Todo.class, pageRequest);
    }

    @Override
    public Todo get(int idx) {
        Map<String, Object> params = new HashMap<>();
        params.put("todoIdx", idx);
        List<Todo> todos = _repositoryHelper.query("SELECT * FROM todo_tbl WHERE idx = :todoIdx", Todo.class, params);
        return todos.isEmpty() ? null : todos.get(0);
    }

}
