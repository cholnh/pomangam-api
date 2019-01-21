package com.mrporter.pomangam.common.map.controller;

import com.mrporter.pomangam.common.map.repository.CommonMapRepository;
import com.mrporter.pomangam.common.map.domain.CommonMap;
import com.mrporter.pomangam.common.map.domain.CommonMapDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
