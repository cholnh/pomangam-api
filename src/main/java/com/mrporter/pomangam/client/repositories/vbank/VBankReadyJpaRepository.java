package com.mrporter.pomangam.client.repositories.vbank;

import com.mrporter.pomangam.client.domains.vbank.QVBankReady;
import com.mrporter.pomangam.client.domains.vbank.VBankReady;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface VBankReadyJpaRepository extends JpaRepository<VBankReady, Long>, VBankReadyCustomRepository {
    void deleteByRegisterDateBefore(LocalDateTime when);
}

interface VBankReadyCustomRepository {
    List<VBankReady> findReady(String name, Integer input);
}

@Transactional(readOnly = true)
class VBankReadyCustomRepositoryImpl extends QuerydslRepositorySupport implements VBankReadyCustomRepository {

    public VBankReadyCustomRepositoryImpl() {
        super(VBankReady.class);
    }

    @Override
    public List<VBankReady> findReady(String name, Integer input) {
        QVBankReady vbankReady = QVBankReady.vBankReady;
        return from(vbankReady)
                .select(vbankReady)
                .where(vbankReady.name.eq(name)
                .and(vbankReady.input.eq(input))
                .and(vbankReady.registerDate.after(LocalDateTime.now().minusDays(1))))
                .fetch();
    }
}