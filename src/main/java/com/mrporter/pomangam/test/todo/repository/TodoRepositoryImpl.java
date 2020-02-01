package com.mrporter.pomangam.test.todo.repository;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import com.mrporter.pomangam.productEntry.product.domain.ProductWithCostDto;
import com.mrporter.pomangam.test.home.domain.HomeTbl;
import com.mrporter.pomangam.test.todo.domain.Todo;
import lombok.AllArgsConstructor;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
