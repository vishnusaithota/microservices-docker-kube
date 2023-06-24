package com.spiderweb.notification.controller;

import com.spiderweb.clients.notification.NotificationRequest;
import com.spiderweb.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping()
    public Boolean sendNotification(
            @RequestBody NotificationRequest notificationRequest){
        log.info("Notification Request received : {}",notificationRequest);
        notificationService.sendNotification(notificationRequest);
        return true;
    }
}
