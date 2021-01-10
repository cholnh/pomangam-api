package com.mrporter.pomangam.client.services.fcm;

import com.mrporter.pomangam.admin.repositories.staff.StaffJpaRepository;
import com.mrporter.pomangam.client.domains.fcm.FcmRequestDto;
import com.mrporter.pomangam.client.domains.fcm.FcmTokenDto;
import com.mrporter.pomangam.client.domains.fcm.client.FcmClientToken;
import com.mrporter.pomangam.client.domains.fcm.client.FcmClientTokenDto;
import com.mrporter.pomangam.client.domains.fcm.owner.FcmOwnerToken;
import com.mrporter.pomangam.client.domains.fcm.owner.FcmOwnerTokenDto;
import com.mrporter.pomangam.client.domains.fcm.staff.FcmStaffToken;
import com.mrporter.pomangam.client.domains.fcm.staff.FcmStaffTokenDto;
import com.mrporter.pomangam.client.repositories.fcm.client.FcmClientTokenJpaRepository;
import com.mrporter.pomangam.client.repositories.fcm.owner.FcmOwnerTokenJpaRepository;
import com.mrporter.pomangam.client.repositories.fcm.staff.FcmStaffTokenJpaRepository;
import com.mrporter.pomangam.client.repositories.user.UserJpaRepository;
import com.mrporter.pomangam.store.repository.owner.OwnerJpaRepository;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class FcmServiceImpl implements FcmService {

    FcmClientTokenJpaRepository fcmClientTokenRepo;
    FcmOwnerTokenJpaRepository fcmOwnerTokenRepo;
    FcmStaffTokenJpaRepository fcmStaffTokenRepo;
    AndroidPushNotificationsServiceImpl androidPushNotificationsService;
    UserJpaRepository userRepo;
    OwnerJpaRepository ownerRepo;
    StaffJpaRepository staffRepo;

    @Override
    public String send(FcmRequestDto fcmRequest) {
        if(fcmRequest.getTo() == null || fcmRequest.getTo().isEmpty()) return null;

        String firebaseResponse = null;
        JSONObject body = new JSONObject();
        JSONArray array = new JSONArray();

        // get token from db
        for(FcmTokenDto token : fcmRequest.getTo()) {
            // System.out.println("token : " + token.getToken());
            array.put(token.getToken());
        }

        // set notification
        JSONObject notification = new JSONObject();
        notification.put("title", fcmRequest.getTitle());
        notification.put("body", fcmRequest.getBody());
        notification.put("sound", fcmRequest.getSound() == null ? "default" : fcmRequest.getSound());
        if(fcmRequest.getImage() != null) {
            notification.put("image", fcmRequest.getImage());
        }

        if(array.length() == 1) {
            body.put("to", array.get(0));
        } else {
            body.put("registration_ids", array);
        }
        body.put("notification", notification);
        body.put("data", fcmRequest.getData());

        HttpEntity<String> request = new HttpEntity<>(body.toString());
        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            firebaseResponse = pushNotification.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return firebaseResponse;
    }

    @Override
    public FcmClientTokenDto postClient(FcmClientTokenDto token) {
        assert token != null && token.getToken() != null && !token.getToken().isEmpty();
        FcmClientToken clientToken = fcmClientTokenRepo.findByToken(token.getToken());
        if(clientToken != null) {
            if(token.getPhoneNumber() != null && token.getPhoneNumber() != clientToken.getPhoneNumber()) {
                clientToken.setPhoneNumber(token.getPhoneNumber());
                clientToken = fcmClientTokenRepo.save(clientToken);
            }
        } else {
            token.setRegisterDate(LocalDateTime.now());
            clientToken = fcmClientTokenRepo.save(token.toEntity());
        }
        return FcmClientTokenDto.fromEntity(clientToken);
    }

    @Override
    public FcmOwnerTokenDto postOwner(FcmOwnerTokenDto token) {
        assert token != null && token.getToken() != null && !token.getToken().isEmpty();
        FcmOwnerToken ownerToken = fcmOwnerTokenRepo.findByToken(token.getToken());
        if(ownerToken != null) {
            if(token.getId() != null && token.getId() != ownerToken.getIdOwner()) {
                ownerToken.setIdOwner(token.getId());
                ownerToken = fcmOwnerTokenRepo.save(ownerToken);
            }
        } else {
            token.setRegisterDate(LocalDateTime.now());
            ownerToken = fcmOwnerTokenRepo.save(token.toEntity());
        }
        return FcmOwnerTokenDto.fromEntity(ownerToken);
    }

    @Override
    public FcmStaffTokenDto postStaff(FcmStaffTokenDto token) {
        assert token != null && token.getToken() != null && !token.getToken().isEmpty();
        FcmStaffToken staffToken = fcmStaffTokenRepo.findByToken(token.getToken());
        if(staffToken != null) {
            if(token.getId() != null && token.getId() != staffToken.getIdStaff()) {
                staffToken.setIdStaff(token.getId());
                staffToken = fcmStaffTokenRepo.save(staffToken);
            }
        } else {
            token.setRegisterDate(LocalDateTime.now());
            staffToken = fcmStaffTokenRepo.save(token.toEntity());
        }
        return FcmStaffTokenDto.fromEntity(staffToken);
    }

    @Override
    public void deleteClient(Long fIdx) {
        Optional<FcmClientToken> optional = fcmClientTokenRepo.findById(fIdx);
        if(optional.isPresent()) {
            FcmClientToken clientToken = optional.get();
            clientToken.setPhoneNumber(null);
            fcmClientTokenRepo.save(clientToken);
        }
    }

    @Override
    public void deleteOwner(Long fIdx) {
        Optional<FcmOwnerToken> optional = fcmOwnerTokenRepo.findById(fIdx);
        if(optional.isPresent()) {
            FcmOwnerToken ownerToken = optional.get();
            ownerToken.setIdOwner(null);
            fcmOwnerTokenRepo.save(ownerToken);
        }
    }

    @Override
    public void deleteStaff(Long fIdx) {
        Optional<FcmStaffToken> optional = fcmStaffTokenRepo.findById(fIdx);
        if(optional.isPresent()) {
            FcmStaffToken staffToken = optional.get();
            staffToken.setIdStaff(null);
            fcmStaffTokenRepo.save(staffToken);
        }
    }
}