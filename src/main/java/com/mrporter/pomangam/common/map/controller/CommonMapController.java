package com.mrporter.pomangam.common.map.controller;

import com.mrporter.pomangam.common.map.repository.CommonMapRepository;
import com.mrporter.pomangam.common.map.domain.CommonMapTbl;
import com.mrporter.pomangam.common.map.domain.CommonMapTblDto;
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

    private CommonMapRepository commonMapRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{idx}")
    public ResponseEntity<?> getMap(@PathVariable Long idx) {
        List<CommonMapTbl> list = commonMapRepository.findByCommonMapTblByIdx(idx);
        //System.out.println(list);
        return new ResponseEntity<List<CommonMapTbl>>(list, HttpStatus.OK);
    }

    @PostMapping
    public void postMap(@RequestBody CommonMapTblDto dto) {
        commonMapRepository.save(dto.toEntity());
    }
}
