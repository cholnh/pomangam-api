package com.mrporter.pomangam.deliveryEntry.initialConsonantForDsite.repository;

import com.mrporter.pomangam.deliveryEntry.initialConsonantForDsite.domain.InitialConsonantForDsite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface InitialConsonantForDsiteJpaRepository extends JpaRepository<InitialConsonantForDsite, Integer> {
}
