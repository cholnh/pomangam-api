package com.mrporter.pomangam.client.controllers.deliverysite;

import com.mrporter.pomangam.client.services.deliverysite.DeliverySiteService;
import com.mrporter.pomangam.client.services.deliverysite.DeliverySiteServiceImpl;
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

    DeliverySiteServiceImpl deliverySiteService;

    @GetMapping
    public ResponseEntity<?> get(
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(deliverySiteService.get(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> getByIdx(@PathVariable(value = "idx", required = true) Integer idx
    ) {
        return new ResponseEntity(deliverySiteService.getByIdx(idx), HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> searchCount() {
        return new ResponseEntity(deliverySiteService.count(), HttpStatus.OK);
    }

    @GetMapping("/search/consonant")
    public ResponseEntity<?> searchConsonant(@RequestParam(value = "query", required = true) String query
    ) {
        return new ResponseEntity("아직 개발 안함", HttpStatus.OK);   //  Todo: 초성검색 개발
    }

}
