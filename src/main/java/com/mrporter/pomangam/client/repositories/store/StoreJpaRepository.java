package com.mrporter.pomangam.client.repositories.store;

import com.mrporter.pomangam.client.domains.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface StoreJpaRepository extends JpaRepository<Store, Long>, StoreCustomRepository {
    Page<Store> findByIdxDeliverySiteAndIsActiveIsTrueOrderBySequenceAsc(Long dIdx, Pageable pageable);
    Store findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
}

interface StoreCustomRepository {

}

@Transactional(readOnly = true)
class StoreCustomRepositoryImpl extends QuerydslRepositorySupport implements StoreCustomRepository {

    public StoreCustomRepositoryImpl() {
        super(Store.class);
    }

}