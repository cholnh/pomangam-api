package com.mrporter.pomangam.admin.controllers.deliverysite;

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
@RequestMapping("/admin/dsites")
@AllArgsConstructor
public class _DeliverySiteController {

    @GetMapping
    public ResponseEntity<?> get(Pageable pageable) {
        System.out.println(pageable);
        System.out.println(pageable == null);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getByIdx(@PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> searchCount() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/search/consonant")
    public ResponseEntity<?> searchConsonant(@RequestParam(value = "query", required = true) String query
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

}
