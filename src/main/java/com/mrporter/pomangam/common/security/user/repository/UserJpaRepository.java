package com.mrporter.pomangam.common.security.user.repository;

import com.mrporter.pomangam.common.security.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    User findById(@Param("id") String id);
    User findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    User findByIdAndPhoneNumber(@Param("id") String id,
                                @Param("phoneNumber") String phoneNumber);
    void deleteById(@Param("id") String id);
}
