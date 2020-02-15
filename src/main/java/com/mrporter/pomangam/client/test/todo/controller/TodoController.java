package com.mrporter.pomangam.client.test.todo.controller;

import com.mrporter.pomangam.client.test.todo.domain.PageRequest;
import com.mrporter.pomangam.client.test.todo.domain.TodoDto;
import com.mrporter.pomangam.client.test.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@AllArgsConstructor
public class TodoController {

    private final TodoService _todoService;

    @GetMapping("/size")
    public ResponseEntity<?> count() {
        return new ResponseEntity(_todoService.count(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> get(
            PageRequest pageRequest
    ) {
        List<TodoDto> results =  _todoService.get(pageRequest);
        return new ResponseEntity(results, HttpStatus.OK);
    }

    @GetMapping("/{todoIdx}")
    public ResponseEntity<?> get(
            @PathVariable(name = "todoIdx") Integer todoIdx
    ) {
        TodoDto result =  _todoService.get(todoIdx);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody TodoDto todo) {
        try {
            TodoDto result =  _todoService.post(todo);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/{todoIdx}")
    public ResponseEntity<?> delete(@PathVariable int todoIdx) {
        _todoService.delete(todoIdx);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> put(@RequestBody TodoDto todo) {
        TodoDto result =  _todoService.put(todo);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> patch(@RequestBody TodoDto todo) {
        TodoDto result =  _todoService.patch(todo);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
