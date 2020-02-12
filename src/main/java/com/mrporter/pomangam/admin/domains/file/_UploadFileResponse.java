package com.mrporter.pomangam.admin.domains.file;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class _UploadFileResponse implements Serializable {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    @Builder
    public _UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
