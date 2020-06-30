package com.mrporter.pomangam.store.controllers.owner;

import com.mrporter.pomangam.store.domains.owner.OwnerDto;
import com.mrporter.pomangam.store.services.owner.OwnerServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/store/owners")
@AllArgsConstructor
public class OwnerController {

    OwnerServiceImpl ownerService;

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_STORE_OWNER', 'ROLE_ADMIN', 'ROLE_STAFF'))")
    @PostAuthorize("( returnObject != null && returnObject.id == principal.username ) or hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF')")
    @GetMapping("/{id}")
    public OwnerDto searchInfo(
            @PathVariable(value = "id") String id,
            Principal principal
    ) {
        return ownerService.findById(id);
    }

    @GetMapping("/search/exist/id")
    public ResponseEntity<?> isExistById(
            @RequestParam(value = "id", required = true) String id
    ) {
        return new ResponseEntity(ownerService.isExistById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(
            @RequestBody(required = true) OwnerDto dto
    ) {
        return new ResponseEntity<>(ownerService.saveOwner(dto.toEntity()), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF') or ( #id == principal.username ))")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable(value = "id") String id
    ) {
        return new ResponseEntity<>(ownerService.deleteOwner(id), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated() and (hasAnyRole('ROLE_ADMIN', 'ROLE_STAFF') or ( #id == principal.username ))")
    @PatchMapping("/{id}")
    public ResponseEntity patch(
            @PathVariable(value = "id") String id,
            @RequestBody(required = true) OwnerDto dto
    ) {
        return new ResponseEntity<>(ownerService.patchOwner(id, dto.toEntity()), HttpStatus.OK);
    }
}
