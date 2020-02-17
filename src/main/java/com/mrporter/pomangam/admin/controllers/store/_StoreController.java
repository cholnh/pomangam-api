package com.mrporter.pomangam.admin.controllers.store;

import com.mrporter.pomangam.admin.services.store._StoreServiceImpl;
import com.mrporter.pomangam.client.services.store.StoreServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_STAFF')")
@RestController
@RequestMapping("/admin/dsites/{dIdx}/stores")
@AllArgsConstructor
public class _StoreController {

    _StoreServiceImpl storeService;

    @GetMapping
    public ResponseEntity<?> get(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getByIdx(
            @PathVariable(value = "dIdx", required = true) Long dIdx,
            @PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> searchCount(
            @PathVariable(value = "dIdx", required = true) Long dIdx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/summary")
    public ResponseEntity<?> searchSummary(
            @PathVariable(value = "dIdx", required = true) Long dIdx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
