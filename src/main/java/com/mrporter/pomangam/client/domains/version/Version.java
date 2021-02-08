package com.mrporter.pomangam.client.domains.version;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Version implements Serializable {
    int latestVersion;
    int minimumVersion;

    @Builder
    public Version(int latestVersion, int minimumVersion) {
        this.latestVersion = latestVersion;
        this.minimumVersion = minimumVersion;
    }
}
