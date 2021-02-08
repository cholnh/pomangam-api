package com.mrporter.pomangam.client.services.vbank;

import com.mrporter.pomangam._bases.utils.bizm.template.VBankDepositTemplate;
import com.mrporter.pomangam.client.domains.map.CommonMap;
import com.mrporter.pomangam.client.domains.vbank.VBankDeposit;
import com.mrporter.pomangam.client.domains.vbank.VBankDepositDto;
import com.mrporter.pomangam.client.domains.vbank.VBankReady;
import com.mrporter.pomangam.client.domains.vbank.VBankStatus;
import com.mrporter.pomangam.client.repositories.order.OrderJpaRepository;
import com.mrporter.pomangam.client.repositories.vbank.VBankDepositJpaRepository;
import com.mrporter.pomangam.client.repositories.vbank.VBankReadyJpaRepository;
import com.mrporter.pomangam.client.services.map.CommonMapServiceImpl;
import com.mrporter.pomangam.client.services.order.OrderServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@AllArgsConstructor
public class VBankServiceImpl implements VBankService {

    VBankDepositJpaRepository vBankDepositRepo;
    VBankReadyJpaRepository vBankReadyRepo;
    OrderServiceImpl orderService;
    OrderJpaRepository orderRepo;
    CommonMapServiceImpl commonMapService;

    @Override
    public List<VBankDepositDto> findDeposit(Pageable pageable) {
        List<VBankDeposit> deposits = vBankDepositRepo.findAll(pageable).getContent();
        return VBankDepositDto.fromEntities(deposits);
    }

    @Override
    @Transactional
    public void autoCheckDeposit(boolean isForceUpdate) {
        if(isOnService()) {
            if(!isForceUpdate && isReadyEmpty()) return;
            List<VBankDepositDto> deposits = parseVBank();

            for(int i=deposits.size()-1; i>=0; i--) {
                VBankDeposit saved = vBankDepositRepo.save(deposits.get(i).toEntity());
                checkVBank(saved);
            }
        }
    }

    @Override
    @Transactional
    public void clearOldReady() {
        if(isOnService()) {
            log.info("clearOldReady");
            vBankReadyRepo.deleteByRegisterDateBefore(LocalDateTime.now().minusDays(2));
        }
    }

    private boolean isReadyEmpty() {
        return vBankReadyRepo.count() == 0;
    }

    private boolean isOnService() {
        String val = commonMapService.findValueByKey("boolean_vbank_service_onoff");
        return val.toLowerCase().equals("true");
    }

    private void checkVBank(VBankDeposit deposit) {
        try {
            List<VBankReady> readies = vBankReadyRepo.findReady(deposit.getName(), deposit.getInput());
            if(readies == null || readies.isEmpty()) {
                // 매칭실패 - 입금자 없음
                deposit.setStatus(VBankStatus.FAIL_UNKNOWN_NAME);
                vBankDepositRepo.save(deposit);
                sendNotify(deposit, "입금자 정보 없음");
            } else if(readies.size() > 1) {
                // 매칭 실패 - 동명이인
                deposit.setStatus(VBankStatus.FAIL_SAME_NAME);
                vBankDepositRepo.save(deposit);
                sendNotify(deposit, "동명" + readies.size() + "인");
            } else {
                // 매칭 성공
                deposit.setStatus(VBankStatus.SUCCESS);
                vBankDepositRepo.save(deposit);
                orderService.callback(readies.get(0).getIdxOrder());
                vBankReadyRepo.delete(readies.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendNotify(deposit, "서버 내부 에러");
        }
    }

    private void sendNotify(VBankDeposit deposit, String cause) {
        try {
            List<String> admins = new ArrayList<>();
            List<CommonMap> adminPhoneNumbers = commonMapService.findAllByKey("string_phonenumber_vbank_admin");
            for(CommonMap adminPhoneNumber : adminPhoneNumbers) {
                admins.add(adminPhoneNumber.getValue());
            }

            Map<String, String> data = new HashMap<>();
            data.put("name", deposit.getName());
            data.put("input", deposit.getInput()+"원");
            data.put("tid", deposit.getIdx()+"");
            data.put("cause", cause);

            Map<String, Object> button = new HashMap<>();
            button.put("name", "관리자 페이지");
            button.put("type", "WL");
            button.put("url_mobile", "https://www.pomangam.com/admin");
            button.put("url_pc", "https://www.pomangam.com/admin");

            VBankDepositTemplate.send(admins, data, button);
        } catch (Exception msgException) {
            msgException.printStackTrace();
        }
    }

    private List<VBankDepositDto> parseVBank() {
        List<VBankDepositDto> result = new ArrayList<>();
        try {
            // db에 저장된 최상위 내역 1개 patch
            VBankDepositDto last = VBankDepositDto.fromEntity(vBankDepositRepo.findTopByOrderByIdxDesc());

            // parse by kb bank
            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
            Calendar today = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            parameters.add("계좌번호", "59800101361949");
            parameters.add("고객식별번호", "MRPORTER92");
            parameters.add("비밀번호", "0272");
            parameters.add("빠른조회", "Y");
            parameters.add("응답방법", "2");
            parameters.add("조회구분", "3");
            parameters.add("USER_TYPE", "02");
            parameters.add("_FILE_NAME", "KB_거래내역빠른조회.html");
            parameters.add("_LANG_TYPE", "KOR");
            parameters.add("조회시작일", sdf.format(today.getTime()));
            parameters.add("조회종료일", sdf.format(today.getTime()));

            // entity
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity formEntity = new HttpEntity<>(parameters, headers);

            // post
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
            ResponseEntity firebaseResponse = restTemplate.postForEntity("https://obank.kbstar.com/quics?asfilecode=524517", formEntity, String.class);
            CompletableFuture<ResponseEntity> pushNotification = CompletableFuture.completedFuture(firebaseResponse);
            CompletableFuture.allOf(pushNotification).join();

            // parse
            Document document = Jsoup.parse(pushNotification.get().getBody().toString());
            Elements trs = document.select("tr[align]");
            for(Element tr: trs) {
                Elements tds = tr.getElementsByTag("td");
                VBankDepositDto fetch = VBankDepositDto.builder()
                        .transferDate(tds.get(0).text())
                        .content(tds.get(1).text())
                        .name(tds.get(2).text())
                        .input(Integer.parseInt(tds.get(5).text().replaceAll(",", "")))
                        .remain(Integer.parseInt(tds.get(6).text().replaceAll(",", "")))
                        .bank(tds.get(7).text())
                        .status(VBankStatus.READY)
                        .build();

                // 중복검사
                if(last != null && last.equals(fetch)) {
                    break;
                } else {
                    result.add(fetch);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
