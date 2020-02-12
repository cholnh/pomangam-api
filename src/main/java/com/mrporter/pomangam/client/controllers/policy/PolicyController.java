package com.mrporter.pomangam.client.controllers.policy;

import com.mrporter.pomangam.client.services._bases.PolicyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/policies")
@RestController
@AllArgsConstructor
public class PolicyController {

    PolicyServiceImpl policyService;

    @GetMapping("/getPrivacy")
    public String getPrivacy() {
        return policyService.getPolicy("privacy");
    }

    @GetMapping("/getTerms")
    public String getTerms() {
        return policyService.getPolicy("terms");
    }
}
