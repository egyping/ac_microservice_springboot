package com.pythonarabia.notifications;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerName,
        String message
) {

}
