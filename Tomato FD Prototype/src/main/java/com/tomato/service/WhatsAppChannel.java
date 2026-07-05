package com.tomato.service;

import com.tomato.order.Order;

public class WhatsAppChannel implements INotificationChannel {
    @Override
    public void send(Order order) {
        if (order == null) return;
        System.out.printf("  => [WhatsApp Notification] Sent confirmation message to WhatsApp linked with %s's profile for Order #%d.%n", 
            order.getUser().getName(), order.getId());
    }
}
