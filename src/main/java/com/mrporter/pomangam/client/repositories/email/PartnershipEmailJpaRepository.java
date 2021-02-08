package com.mrporter.pomangam.client.repositories.email;
import com.mrporter.pomangam.client.domains.email.PartnershipEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface PartnershipEmailJpaRepository extends JpaRepository<PartnershipEmail, Long>, PartnershipEmailCustomRepository {

}

interface PartnershipEmailCustomRepository {

}

@Transactional(readOnly = true)
class PartnershipEmailCustomRepositoryImpl extends QuerydslRepositorySupport implements PartnershipEmailCustomRepository {

    public PartnershipEmailCustomRepositoryImpl() {
        super(PartnershipEmail.class);
    }


}