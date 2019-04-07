package com.mrporter.pomangam.common.security.authority.repository;

import com.mrporter.pomangam.common.security.authority.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AuthorityJpaRepository extends JpaRepository<Authority, Integer> {
    Authority findByUserId(@Param("user_id") String id);
}
