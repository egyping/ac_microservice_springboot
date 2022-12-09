package com.pythonarabia.notifications;

import com.pythonarabia.fraud.FraudCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@FeignClient("notifications")
public interface NotificationsClient {

    @PostMapping(path = "api/v1/notifications")
    void sendNotification(NotificationRequest notificationRequest);

}
