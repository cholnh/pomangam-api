package com.mrporter.pomangam._bases.aspects.test;

import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
@AllArgsConstructor
public class AopController {

    RealClass realClass;

    @GetMapping
    public ResponseEntity<?> get() throws JSONException {
        System.out.println("controller_start_1");
        String r1 = realClass.doSomething_1();
        System.out.println("controller_end_1");
        System.out.println("controller_start_2");
        String r2 = realClass.doSomething_2();
        System.out.println("controller_end_2");
        return ResponseEntity.ok(r1 + " " + r2);
    }
}
