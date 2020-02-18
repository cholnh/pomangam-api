package com.mrporter.pomangam.client.repositories.fcm;

import com.mrporter.pomangam.client.domains.fcm.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface FcmTokenJpaRepository extends JpaRepository<FcmToken, Long>, FcmTokenCustomRepository {
    
}

interface FcmTokenCustomRepository {
   
}

@Transactional(readOnly = true)
class FcmTokenCustomRepositoryImpl extends QuerydslRepositorySupport implements FcmTokenCustomRepository {

    public FcmTokenCustomRepositoryImpl() {
        super(FcmToken.class);
    }
    
    
}
