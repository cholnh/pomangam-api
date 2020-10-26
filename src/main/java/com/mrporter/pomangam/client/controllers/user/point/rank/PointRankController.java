package com.mrporter.pomangam.client.controllers.user.point.rank;

import com.mrporter.pomangam.client.services.user.point.rank.PointRankServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/points")
@AllArgsConstructor
public class PointRankController {

    PointRankServiceImpl pointRankService;

    @GetMapping("/rank")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity(pointRankService.findAll(), HttpStatus.OK);
    }
}
