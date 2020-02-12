package com.mrporter.pomangam._bases.securities.oauth2clientdetail.repository;

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
