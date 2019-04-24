package com.mrporter.pomangam.common.security.oauth2ClientDetail.repository;

import com.mrporter.pomangam.common.security.oauth2ClientDetail.domain.Oauth2ClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = true)
public interface Oauth2ClientDetailJpaRepository extends JpaRepository<Oauth2ClientDetail, String> {
}
