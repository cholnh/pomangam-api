package com.mrporter.pomangam.client.controllers.carte;

import com.mrporter.pomangam.client.services.carte.CarteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartes")
@AllArgsConstructor
public class CarteController {

    private CarteService carteService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(carteService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/index")
    public ResponseEntity<?> findIndexCartes(
                @PageableDefault(sort = {"idx"}, direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(carteService.findIndexCartes(pageable), HttpStatus.OK);
    }

    @GetMapping("/today")
    public ResponseEntity<?> today() {
        return new ResponseEntity(carteService.today(), HttpStatus.OK);
    }
}
