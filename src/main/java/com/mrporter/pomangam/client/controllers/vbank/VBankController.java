package com.mrporter.pomangam.client.controllers.vbank;

import com.mrporter.pomangam.client.services.vbank.VBankServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vbank")
@AllArgsConstructor
public class VBankController {

    VBankServiceImpl vBankService;

//    @GetMapping
//    public @ResponseBody ResponseEntity<?> bank(){
//        vBankService.autoCheckDeposit(false);
//
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<?> findDeposit(
            @PageableDefault(sort = {"idx"}, direction = Sort.Direction.DESC, page = 0, size = 10) Pageable pageable
    ) {
        return new ResponseEntity(vBankService.findDeposit(pageable), HttpStatus.OK);
    }
}
