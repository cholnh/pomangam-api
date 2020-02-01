package com.mrporter.pomangam.test.todo.repository;

import com.mrporter.pomangam.productEntry.product.domain.PageRequest;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Repository
public class RepositoryHelper {
    @PersistenceContext
    EntityManager em;

    @Autowired
    public RepositoryHelper(EntityManager em) {
        this.em = em;
    }

    public <T> List<T> query(String sql, Class<T> clazz) {
        Query nativeQuery = em.createNativeQuery(sql);

        List<T> list = nativeQuery
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( clazz ) )
                .getResultList();
        return list;
    }

    public <T> List<T> query(String sql, Class<T> clazz, PageRequest pageRequest) {
        Query nativeQuery = em.createNativeQuery(sql);

        List<T> list = nativeQuery
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( clazz ) )
                .setFirstResult(pageRequest.getFirstIndex())
                .setMaxResults(pageRequest.getSize())
                .getResultList();
        return list;
    }

    public <T> List<T> query(String sql, Class<T> clazz, Map<String, Object> params) {
        Query nativeQuery = em.createNativeQuery(sql);

        params.forEach((key, val) -> nativeQuery.setParameter(key, val));

        List<T> list = nativeQuery
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( clazz ) )
                .getResultList();
        return list;
    }

    public <T> List<T> query(String sql, Class<T> clazz, Map<String, Object> params, PageRequest pageRequest) {
        Query nativeQuery = em.createNativeQuery(sql);

        params.forEach((key, val) -> nativeQuery.setParameter(key, val));

        List<T> list = nativeQuery
                .unwrap( org.hibernate.query.NativeQuery.class )
                .setResultTransformer( Transformers.aliasToBean( clazz ) )
                .setFirstResult(pageRequest.getFirstIndex())
                .setMaxResults(pageRequest.getSize())
                .getResultList();
        return list;
    }
}
