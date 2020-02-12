package com.mrporter.pomangam.client.repositories.employee;

import com.mrporter.pomangam.client.domains.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface EmployeeJpaRepository extends JpaRepository<Employee, Integer> {
    Employee findById(@Param("id") String id);
    void deleteById(@Param("id") String id);
}

