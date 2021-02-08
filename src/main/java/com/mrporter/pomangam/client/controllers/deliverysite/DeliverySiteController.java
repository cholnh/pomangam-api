package com.mrporter.pomangam.client.controllers.deliverysite;

import com.mrporter.pomangam.client.domains.deliverysite.DeliverySiteDto;
import com.mrporter.pomangam.client.services.deliverysite.DeliverySiteServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dsites")
@AllArgsConstructor
public class DeliverySiteController {

    DeliverySiteServiceImpl deliverySiteService;

    @GetMapping
    public ResponseEntity<List<DeliverySiteDto>> findAll(
            @RequestParam(value = "sIdx", required = false) Long sIdx,
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, page = 0, size = 100) Pageable pageable
    ) {
        if(sIdx == null) {
            return new ResponseEntity(deliverySiteService.findAll(pageable), HttpStatus.OK);
        } else {
            return new ResponseEntity(deliverySiteService.findAllByIdxStore(sIdx), HttpStatus.OK);
        }
    }

    @GetMapping("/{idx}")
    public ResponseEntity<DeliverySiteDto> findByIdx(
            @PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(deliverySiteService.findByIdx(idx), HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity(deliverySiteService.count(), HttpStatus.OK);
    }

    @GetMapping("/search/consonant")
    public ResponseEntity<?> findConsonant(
            @RequestParam(value = "query", required = true) String query
    ) {
        return new ResponseEntity("아직 개발 안함", HttpStatus.OK);   //  Todo: 초성검색 개발
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam(value = "query", required = true) String query
    ) {
        return new ResponseEntity(deliverySiteService.search(query), HttpStatus.OK);
    }
}
