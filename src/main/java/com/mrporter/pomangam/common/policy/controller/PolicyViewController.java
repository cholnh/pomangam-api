package com.mrporter.pomangam.common.policy.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/policies")
@Controller
@AllArgsConstructor
public class PolicyViewController {

    @GetMapping("/privacy")
    public String getPrivacyView(Model model) {
        return "policy/privacy/default";
    }

    @GetMapping("/terms")
    public String getTermsView(Model model) {
        return "policy/terms/default";
    }
}
