package com.mrporter.pomangam.client.repositories.fcm.client;

import com.mrporter.pomangam.client.domains.fcm.client.FcmClientToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource(exported = false)
public interface FcmClientTokenJpaRepository extends JpaRepository<FcmClientToken, Long>, FcmClientTokenCustomRepository {
    FcmClientToken findByIdxAndIsActiveIsTrue(Long idx);
    FcmClientToken findByToken(String token);
}

interface FcmClientTokenCustomRepository {
   
}

@Transactional(readOnly = true)
class FcmClientTokenCustomRepositoryImpl extends QuerydslRepositorySupport implements FcmClientTokenCustomRepository {

    public FcmClientTokenCustomRepositoryImpl() {
        super(FcmClientToken.class);
    }
    
    
}
