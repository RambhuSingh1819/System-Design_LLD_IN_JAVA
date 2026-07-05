package com.tomato.service;

import com.tomato.order.Order;
import java.util.ArrayList;
import java.util.List;

public class NotificationManager extends NotificationService {
    private final List<INotificationChannel> channels;

    public NotificationManager(Order order) {
        super(order);
        channels = new ArrayList<>();

        channels.add(new GmailChannel());
        channels.add(new WhatsAppChannel());
        channels.add(new PhoneChannel());
    }

    public void addChannel(INotificationChannel channel) {
        if (channel == null) {
            throw new IllegalArgumentException("Cannot add null channel");
        }
        channels.add(channel);
    }

    public List<INotificationChannel> getChannels() {
        return new ArrayList<>(channels);
    }

    @Override
    public void notifyUser() {
        if (order == null) {
            System.out.println("[Notification Manager] Warning: No order assigned to notify.");
            return;
        }

        System.out.println("            TOMATO ORDER NOTIFICATIONS            ");

        System.out.printf("Broadcasting order confirmation across active channels for Order #%d:%n", order.getId());

        for (INotificationChannel channel : channels) {
            channel.send(order);
        }

        System.out.println("==================================================\n");
    }
}
