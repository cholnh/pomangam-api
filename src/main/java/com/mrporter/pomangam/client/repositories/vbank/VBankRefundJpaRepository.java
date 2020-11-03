package com.mrporter.pomangam.client.repositories.vbank;
import com.mrporter.pomangam.client.domains.vbank.VBankRefund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(exported = false)
public interface VBankRefundJpaRepository extends JpaRepository<VBankRefund, Long>, VBankRefundCustomRepository {

}

interface VBankRefundCustomRepository {

}

@Transactional(readOnly = true)
class VBankRefundCustomRepositoryImpl extends QuerydslRepositorySupport implements VBankRefundCustomRepository {

    public VBankRefundCustomRepositoryImpl() {
        super(VBankRefund.class);
    }


}