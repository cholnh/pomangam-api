package com.mrporter.pomangam.store.services.owner;

import com.mrporter.pomangam.store.domains.owner.Owner;
import com.mrporter.pomangam.store.domains.owner.OwnerDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OwnerService {

    OwnerDto findById(String id);

    Long findIdxById(String id);

    List<OwnerDto> findAll();

    List<OwnerDto> findAll(Pageable pageable);

    OwnerDto saveOwner(Owner owner);

    OwnerDto updateOwnerPassword(String id, String password);

    Boolean isExistById(String id);

    OwnerDto patchOwner(String id, Owner owner);

    Boolean deleteOwner(String id);
}
