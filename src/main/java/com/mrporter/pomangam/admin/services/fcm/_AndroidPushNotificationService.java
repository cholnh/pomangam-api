package com.mrporter.pomangam.admin.services.fcm;

import org.springframework.http.HttpEntity;

import java.util.concurrent.CompletableFuture;

public interface _AndroidPushNotificationService {
    CompletableFuture<String> send(HttpEntity<String> entity);
}
