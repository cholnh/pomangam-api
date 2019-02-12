package com.mrporter.pomangam.common.map.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map")
@AllArgsConstructor
public class CommonMapController {


    /*
    private CommonMapRepository commonMapRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{idx}")
    public ResponseEntity<?> getMap(@PathVariable Long idx) {
        CommonMap list = commonMapRepository.findByIdx(idx);
        //System.out.println(list);
        return new ResponseEntity<CommonMap>(list, HttpStatus.OK);
    }

    @PostMapping
    public void postMap(@RequestBody CommonMapDto dto) {
        commonMapRepository.save(dto.toEntity());
    }
    */
}
