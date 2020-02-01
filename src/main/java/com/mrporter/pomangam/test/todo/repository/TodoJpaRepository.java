package com.mrporter.pomangam.test.todo.repository;

import com.mrporter.pomangam.test.todo.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface TodoJpaRepository extends JpaRepository<Todo, Integer> {
}
