package com.mrporter.pomangam.client.services.fcm;

import com.mrporter.pomangam.client.domains.fcm.FcmRequestDto;
import com.mrporter.pomangam.client.domains.fcm.client.FcmClientTokenDto;
import com.mrporter.pomangam.client.domains.fcm.owner.FcmOwnerTokenDto;
import com.mrporter.pomangam.client.domains.fcm.staff.FcmStaffTokenDto;

public interface FcmService {
    String send(FcmRequestDto fcmRequest);
    FcmClientTokenDto postClient(FcmClientTokenDto token);
    FcmOwnerTokenDto postOwner(FcmOwnerTokenDto token);
    FcmStaffTokenDto postStaff(FcmStaffTokenDto token);
    void deleteClient(Long fIdx);
    void deleteOwner(Long fIdx);
    void deleteStaff(Long fIdx);

}
