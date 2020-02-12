package com.mrporter.pomangam.client.services.fcm;

import org.springframework.http.HttpEntity;

import java.util.concurrent.CompletableFuture;

public interface AndroidPushNotificationService {
    CompletableFuture<String> send(HttpEntity<String> entity);
}
