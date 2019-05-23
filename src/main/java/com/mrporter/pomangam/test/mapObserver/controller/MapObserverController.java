package com.mrporter.pomangam.test.mapObserver.controller;

import com.mrporter.pomangam.test.mapObserver.domain.Map;
import com.mrporter.pomangam.test.mapObserver.service.MapObserverServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tests/maps")
@Slf4j
@AllArgsConstructor
public class MapObserverController {

    MapObserverServiceImpl mapObserverService;

    @GetMapping("/{employeeIdx}")
    public ResponseEntity getBy(@PathVariable("employeeIdx") Integer employeeIdx){
        return new ResponseEntity(mapObserverService.getBy(employeeIdx), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Map map){
        return new ResponseEntity( mapObserverService.post(map), HttpStatus.OK);
    }

    @PatchMapping("/{employeeIdx}")
    public ResponseEntity patch(@PathVariable("employeeIdx") Integer employeeIdx,
                                @RequestBody Map map){
        return new ResponseEntity(mapObserverService.patch(employeeIdx, map), HttpStatus.OK);
    }

}
