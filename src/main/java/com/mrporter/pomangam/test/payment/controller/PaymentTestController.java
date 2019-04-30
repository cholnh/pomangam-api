package com.mrporter.pomangam.test.payment.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tests/page")
@AllArgsConstructor
public class PaymentTestController {

    @GetMapping()
    public String get(Model model) {

        model.addAttribute("title", "Payment Test");

        return "payment";
    }
}


