package com.mrporter.pomangam.client.controllers.policy;

import com.mrporter.pomangam.client.services._bases.CommonMapServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("/policies")
@Controller
@AllArgsConstructor
public class PolicyViewController {

    CommonMapServiceImpl commonMapService;

    @GetMapping("/privacy")
    public String getPrivacyView() {
        return "policy/privacy/default";
    }

    @GetMapping("/terms")
    public String getTermsView() {
        return "policy/terms/default";
    }

    @GetMapping("/intro")
    public String getIntroView() {
        return "policy/intro/default";
    }

    @GetMapping("/info")
    public RedirectView getInfoView() {
        String companyNumber = commonMapService.getValue("company-number").get(0).getValue();
        return new RedirectView("http://www.ftc.go.kr/bizCommPop.do?wrkr_no=" + companyNumber);
    }
}
