package com.mrporter.pomangam.admin.repositories.user;

import com.mrporter.pomangam.client.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface _UserJpaRepository extends JpaRepository<User, Long> {
    User findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    void deleteByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
