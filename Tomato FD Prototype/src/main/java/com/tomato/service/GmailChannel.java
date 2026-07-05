package com.tomato.service;

import com.tomato.order.Order;

public class GmailChannel implements INotificationChannel {
    @Override
    public void send(Order order) {
        if (order == null) return;
        String email = order.getUser().getName().toLowerCase().replace(" ", "") + "@gmail.com";
        System.out.printf("  => [Gmail Notification] Sent email receipt to %s for Order #%d (Total: $%.2f)%n", 
            email, order.getId(), order.getTotalAmount());
    }
}
