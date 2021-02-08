package com.mrporter.pomangam.client.services.email;

import com.mrporter.pomangam._bases.utils.bizm.template.PartnershipEmailTemplate;
import com.mrporter.pomangam.client.domains.email.PartnershipEmailDto;
import com.mrporter.pomangam.client.domains.map.CommonMap;
import com.mrporter.pomangam.client.repositories.email.PartnershipEmailJpaRepository;
import com.mrporter.pomangam.client.services.map.CommonMapServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    JavaMailSender javaMailSender;
    CommonMapServiceImpl commonMapService;
    PartnershipEmailJpaRepository partnershipEmailRepo;

    public void sendPartnershipEmail(PartnershipEmailDto request) {
        // db 저장
        partnershipEmailRepo.save(request.toEntity());

        // 비즈엠 전송
        List<CommonMap> adminPhoneNumbers = commonMapService.findAllByKey("string_phonenumber_partnership_admin");
        for(CommonMap adminPhoneNumber : adminPhoneNumbers) {
            try {
                Map<String, String> data = new HashMap<>();
                data.put("name", request.getClientName());
                data.put("email", request.getClientEmail());
                data.put("contents", request.getContents());
                PartnershipEmailTemplate.send(adminPhoneNumber.getValue(), data);
            } catch (Exception msgException) {
                msgException.printStackTrace();
            }
        }

        // 메일 전송
        List<CommonMap> adminMails = commonMapService.findAllByKey("string_email_partnership_admin");
        for(CommonMap adminMail : adminMails) {
            SimpleMailMessage simpleMessage = new SimpleMailMessage();
            simpleMessage.setFrom("contact@poman.kr");
            simpleMessage.setTo(adminMail.getValue());
            simpleMessage.setSubject("[포만감 제휴문의] " + request.getClientName() + "님 제휴요청 건");
            simpleMessage.setText("고객명 : " + request.getClientName() + "\n" + "이메일 : " + request.getClientEmail() + "\n" + "내용 : " + request.getContents());
            javaMailSender.send(simpleMessage);
        }
    }
}
