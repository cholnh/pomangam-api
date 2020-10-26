package com.mrporter.pomangam.client.domains.fcm;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class FcmRequestDto implements Serializable {

    String title;
    String body;
    String sound;
    String image;
    Map<String, String> data;
    List<FcmTokenDto> to;

    @Builder
    public FcmRequestDto(String title, String body, String sound, String image, Map<String, String> data, List<FcmTokenDto> to) {
        this.title = title;
        this.body = body;
        this.sound = sound;
        this.image = image;
        this.data = data;
        this.to = to;
    }
}
