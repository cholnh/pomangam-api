package com.mrporter.pomangam.client.services.vbank;

import com.mrporter.pomangam.client.domains.vbank.VBankDepositDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VBankService {
    List<VBankDepositDto> findDeposit(Pageable pageable);
    void autoCheckDeposit(boolean isForceUpdate);
    void clearOldReady();
}
