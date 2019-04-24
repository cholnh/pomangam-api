package com.mrporter.pomangam.common.security.oauth2ClientDetail.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@AllArgsConstructor
public class Oauth2ClientDetailRepositoryImpl implements Oauth2ClientDetailRepository {
    @PersistenceContext
    EntityManager em;

}
