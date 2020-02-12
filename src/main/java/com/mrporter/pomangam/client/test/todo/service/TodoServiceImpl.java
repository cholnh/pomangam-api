package com.mrporter.pomangam.client.test.todo.service;

import com.mrporter.pomangam.client.domains._bases.PageRequest;
import com.mrporter.pomangam.client.test.todo.domain.Todo;
import com.mrporter.pomangam.client.test.todo.domain.TodoDto;
import com.mrporter.pomangam.client.test.todo.repository.TodoJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    TodoJpaRepository _todoJpaRepository;

    @Override
    public int count() {
        return (int) _todoJpaRepository.count();
    }

    @Override
    public List<TodoDto> get(PageRequest pageRequest) {
        List<Todo> todos;
        if(pageRequest.of() == null) {
            todos = _todoJpaRepository.findAll();
        } else {
            Page<Todo> page = _todoJpaRepository.findAll(pageRequest.of());
            todos = page.getContent();
        }
        return TodoDto.fromEntities(todos);
    }

    @Override
    public TodoDto get(int idx) {
        Optional<Todo> optional = _todoJpaRepository.findById(idx);
        if(optional.isPresent()) {
            return TodoDto.fromEntity(optional.get());
        } else {
            return null;
        }
    }

    @Override
    public TodoDto post(TodoDto bean) {
        return TodoDto.fromEntity(_todoJpaRepository.save(bean.toEntity()));
    }

    @Override
    public void delete(int idx) {
        _todoJpaRepository.deleteById(idx);
    }

    @Override
    public TodoDto put(TodoDto bean) {
        Todo entity = bean.toEntity();
        entity.setIdx(bean.getIdx());
        return TodoDto.fromEntity(_todoJpaRepository.save(entity));
    }

    @Override
    public TodoDto patch(TodoDto bean) {
        final Optional<Todo> optional = _todoJpaRepository.findById(bean.getIdx());

        if(optional.isPresent()) {
            Todo fetched = optional.get();
            if(bean.getTitle() != null) {
                fetched.setTitle(bean.getTitle());
            }
            if(bean.getContents() != null) {
                fetched.setContents(bean.getContents());
            }
            if(bean.getUsername() != null) {
                fetched.setUsername(bean.getUsername());
            }
            if(bean.getCompleted() != null) {
                fetched.setCompleted(bean.getCompleted() ? Byte.valueOf("1") : Byte.valueOf("0"));
            }
            _todoJpaRepository.save(fetched);
            return  TodoDto.fromEntity(fetched);
        }
        return null;
    }
}
