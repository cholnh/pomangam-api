package com.mrporter.pomangam.store.services._bases;

import com.mrporter.pomangam.store.domains.owner.Owner;
import com.mrporter.pomangam.store.repository.owner.OwnerJpaRepository;
import com.mrporter.pomangam.store.services._bases.exception.StoreAuthenticationException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreAuthenticationServiceImpl implements StoreAuthenticationService {

    OwnerJpaRepository ownerRepo;

    @Override
    public boolean isStoreOwner(Authentication auth, Long sIdx) {
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) ||
                auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
            return true;
        }
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STORE_OWNER"))) {
            Owner owner = ownerRepo.findByIdAndIsActiveIsTrue(auth.getName());
            if(owner.getIdxStore().equals(sIdx)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Long authenticate(Authentication auth, Long sIdx) {
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) ||
                auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STAFF"))) {
            return sIdx;
        }
        if(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_STORE_OWNER"))) {
            Owner owner = ownerRepo.findByIdAndIsActiveIsTrue(auth.getName());
            if(owner.getIdxStore().equals(sIdx)) {
                return sIdx;
            }
        }
        throw new StoreAuthenticationException("INVALID STORE OWNER AUTHENTICATE");
    }
}
