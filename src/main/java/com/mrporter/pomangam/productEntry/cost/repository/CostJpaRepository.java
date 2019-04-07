package com.mrporter.pomangam.productEntry.cost.repository;

import com.mrporter.pomangam.common.security.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface CostJpaRepository extends JpaRepository<User, Integer> {
}
