package com.mrporter.pomangam.client.controllers.deliverysite.region;

import com.mrporter.pomangam.client.services.deliverysite.region.RegionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regions")
@AllArgsConstructor
public class RegionController {

    RegionServiceImpl regionService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(regionService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{idx}")
    public ResponseEntity<?> findByIdx(@PathVariable(value = "idx", required = true) Long idx
    ) {
        return new ResponseEntity(regionService.findByIdx(idx), HttpStatus.OK);
    }

    @GetMapping("/search/count")
    public ResponseEntity<?> count() {
        return new ResponseEntity(regionService.count(), HttpStatus.OK);
    }


}
