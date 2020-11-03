package com.mrporter.pomangam.store.services._bases;

import org.springframework.security.core.Authentication;

public interface StoreAuthenticationService {
    boolean isStoreOwner(Authentication auth, Long sIdx);
    Long authenticate(Authentication auth, Long sIdx);
}
