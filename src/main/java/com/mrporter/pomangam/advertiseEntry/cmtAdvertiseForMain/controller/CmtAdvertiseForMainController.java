package com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.controller;

import com.mrporter.pomangam.advertiseEntry.cmtAdvertiseForMain.service.CmtAdvertiseForMainServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cmtAdvertiseForMains")
@AllArgsConstructor
public class CmtAdvertiseForMainController {

    CmtAdvertiseForMainServiceImpl cmtAdvertiseForMainService;

    @GetMapping("/search/getValue")
    public ResponseEntity<?> getValue(@RequestParam("key") String key) {
        return new ResponseEntity<>(key, HttpStatus.OK);
    }

}
