package com.mrporter.pomangam.client.controllers.vbank;

import com.mrporter.pomangam.client.services.vbank.VBankServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vbank")
@AllArgsConstructor
public class VBankController {

    VBankServiceImpl vBankService;

    @GetMapping
    public @ResponseBody ResponseEntity<?> bank(){
        vBankService.autoCheckDeposit(false);

        return new ResponseEntity(HttpStatus.OK);
    }
}
