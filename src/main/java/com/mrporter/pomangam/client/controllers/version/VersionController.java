package com.mrporter.pomangam.client.controllers.version;

import com.mrporter.pomangam.client.domains.version.Version;
import com.mrporter.pomangam.client.services.map.CommonMapServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/versions")
@AllArgsConstructor
public class VersionController {

    CommonMapServiceImpl commonMapService;

    @GetMapping("/client")
    public ResponseEntity<?> findClient() {
        int latest = Integer.parseInt(commonMapService.findValueByKey("int_latest_version_client"));
        int minimum = Integer.parseInt(commonMapService.findValueByKey("int_minimum_version_client"));

        Version version = Version.builder()
                .latestVersion(latest)
                .minimumVersion(minimum)
                .build();

        return new ResponseEntity<>(version, HttpStatus.OK);
    }

    @GetMapping("/owner")
    public ResponseEntity<?> findOwner() {
        int latest = Integer.parseInt(commonMapService.findValueByKey("int_latest_version_owner"));
        int minimum = Integer.parseInt(commonMapService.findValueByKey("int_minimum_version_owner"));

        Version version = Version.builder()
                .latestVersion(latest)
                .minimumVersion(minimum)
                .build();

        return new ResponseEntity<>(version, HttpStatus.OK);
    }
}
