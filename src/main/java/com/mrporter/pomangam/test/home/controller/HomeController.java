package com.mrporter.pomangam.test.home.controller;

import com.mrporter.pomangam.test.home.domain.HomeTbl;
import com.mrporter.pomangam.test.home.service.HomeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

    private final HomeServiceImpl homeService;

    @GetMapping("/{name}")
    public ResponseEntity<?> get(@PathVariable String name) {
        // 가져오기 서비스 (GET)
        // homeService.get();
        HomeTbl resultBean =  homeService.testService(name);
        return new ResponseEntity<HomeTbl>(resultBean, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> get() {
        // 가져오기 서비스 (GET)
        // homeService.get();
        HomeTbl resultBean =  homeService.testService();
        return new ResponseEntity<HomeTbl>(resultBean, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(HomeTbl bean) {
        // 추가 서비스 (POST)
        // homeService.post();
        return new ResponseEntity<HomeTbl>(bean, HttpStatus.OK);
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<?> delete(@PathVariable String idx) {
        // 삭제 서비스 (DELETE)
        // homeService.delete();
        HomeTbl bean = null;
        return new ResponseEntity<HomeTbl>(bean, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> put(HomeTbl bean) {
        // 수정 서비스 (PUT)
        // homeService.edit();
        return new ResponseEntity<HomeTbl>(bean, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> patch(HomeTbl bean) {
        // 수정 서비스 (PUT)
        // homeService.edit();
        return new ResponseEntity<HomeTbl>(bean, HttpStatus.OK);
    }

}
