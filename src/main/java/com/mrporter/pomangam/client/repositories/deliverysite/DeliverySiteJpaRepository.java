package com.mrporter.pomangam.client.repositories.deliverysite;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface DeliverySiteJpaRepository extends JpaRepository<DeliverySite, Long>, DeliverySiteCustomRepository {
    Page<DeliverySite> findAllByIsActiveIsTrue(Pageable pageable);
    DeliverySite findByIdxAndIsActiveIsTrue(Long idx);
    long countByIsActiveIsTrue();
}

interface DeliverySiteCustomRepository {

}

@Transactional(readOnly = true)
class DeliverySiteCustomRepositoryImpl extends QuerydslRepositorySupport implements DeliverySiteCustomRepository {

    public DeliverySiteCustomRepositoryImpl() {
        super(DeliverySite.class);
    }


}
