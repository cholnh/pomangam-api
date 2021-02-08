package com.mrporter.pomangam.client.controllers.email;

import com.mrporter.pomangam.client.domains.email.PartnershipEmailDto;
import com.mrporter.pomangam.client.services.email.EmailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
@AllArgsConstructor
public class EmailController {

    EmailServiceImpl emailService;

    @PostMapping("/partnership")
    @Async
    public ResponseEntity<?> sendPartnershipMail(
            @RequestBody(required = true) PartnershipEmailDto request
    ) {
        emailService.sendPartnershipEmail(request);
        return new ResponseEntity(HttpStatus.OK);
    }
}
