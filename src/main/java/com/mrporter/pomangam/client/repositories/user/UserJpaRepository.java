package com.mrporter.pomangam.client.repositories.user;

import com.mrporter.pomangam.client.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserJpaRepository extends JpaRepository<User, Integer> {
    User findByPhoneNumber(@Param("phoneNumber") String phoneNumber);
    Boolean existsByNickname(@Param("nickname") String nickname);
    void deleteByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
