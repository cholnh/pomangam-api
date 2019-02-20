package com.mrporter.pomangam.view.main.controller;

import com.mrporter.pomangam.view.main.service.MainServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/views/main")
@RestController
@AllArgsConstructor
public class MainViewController {

    MainServiceImpl mainService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam("didx") Integer delivery_site_idx) {
        return new ResponseEntity<>(mainService.getMainDto(delivery_site_idx), HttpStatus.OK);
    }

    @GetMapping("/first")
    public ResponseEntity<?> getFirst(@RequestParam("didx") Integer delivery_site_idx) {
        return new ResponseEntity<>(mainService.getMainFirstDto(delivery_site_idx), HttpStatus.OK);
    }

    @GetMapping("/second")
    public ResponseEntity<?> getSecond(@RequestParam("didx") Integer delivery_site_idx) {
        return new ResponseEntity<>(mainService.getMainSecondDto(delivery_site_idx), HttpStatus.OK);
    }
}
