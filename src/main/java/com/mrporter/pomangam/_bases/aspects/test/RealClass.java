package com.mrporter.pomangam._bases.aspects.test;

import org.springframework.stereotype.Service;

@Service
public class RealClass {

    public String doSomething_1() {
        System.out.println("doSomething_1");
        return "doSomething 1";
    }

    public String doSomething_2() {
        System.out.println("doSomething_1");
        return "doSomething 2";
    }
}
