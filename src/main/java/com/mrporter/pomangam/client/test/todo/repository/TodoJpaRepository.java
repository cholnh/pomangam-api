package com.mrporter.pomangam.client.test.todo.repository;

import com.mrporter.pomangam.client.test.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TodoJpaRepository extends JpaRepository<Todo, Integer> {
}
