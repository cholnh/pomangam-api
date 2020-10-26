package com.mrporter.pomangam.client.repositories.vbank;

import com.mrporter.pomangam.client.domains.vbank.VBankDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(exported = false)
public interface VBankDepositJpaRepository extends JpaRepository<VBankDeposit, Long>, VBankDepositCustomRepository {
    VBankDeposit findTopByOrderByIdxDesc();
}

interface VBankDepositCustomRepository {

}

@Transactional(readOnly = true)
class VBankDepositCustomRepositoryImpl extends QuerydslRepositorySupport implements VBankDepositCustomRepository {

    public VBankDepositCustomRepositoryImpl() {
        super(VBankDeposit.class);
    }


}