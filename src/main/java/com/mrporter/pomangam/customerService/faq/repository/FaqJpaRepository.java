package com.mrporter.pomangam.customerService.faq.repository;

import com.mrporter.pomangam.customerService.faq.domain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface FaqJpaRepository extends JpaRepository<Faq, Integer> {
}
