package com.mrporter.pomangam.client.controllers.deliverysite;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dsites")
@AllArgsConstructor
public class DeliverySiteController {

    @GetMapping
    public ResponseEntity<?> get(
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getByIdx(@PathVariable(value = "idx", required = true) Integer idx
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
