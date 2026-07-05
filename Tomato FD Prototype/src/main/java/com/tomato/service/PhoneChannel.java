package com.tomato.service;

import com.tomato.order.Order;

public class PhoneChannel implements INotificationChannel {
    @Override
    public void send(Order order) {
        if (order == null)
            return;
        System.out.printf("  => [Phone Notification] Sent SMS receipt to %s's registered mobile number.%n",
                order.getUser().getName());
    }
}
