package com.mrporter.pomangam.common.security.guest.repository;

import com.mrporter.pomangam.common.security.guest.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface GuestJpaRepository extends JpaRepository<Guest, Integer>{
}

