package com.mrporter.pomangam.humanResource.employee.repository;

import com.mrporter.pomangam.humanResource.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {
    Employee findById(@Param("id") String id);
    void deleteById(@Param("id") String id);
}

