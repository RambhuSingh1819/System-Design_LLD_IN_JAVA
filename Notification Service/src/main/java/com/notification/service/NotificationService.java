package com.notification.service;

import com.notification.Notification;
import com.notification.observer.NotificationObservable;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private static NotificationService instance;
    private final List<Notification> notifications = new ArrayList<>();
    private final NotificationObservable observable;

    private NotificationService() {
        this.observable = new NotificationObservable();
    }

    public static synchronized NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public void sendNotification(Notification notification) {
        if (notification != null) {
            notifications.add(notification);
            observable.setNotification(notification);
        }
    }

    public NotificationObservable getObservable() {
        return this.observable;
    }

    public List<Notification> getNotifications() {
        return new ArrayList<>(notifications);
    }


    public void clearHistory() {
        notifications.clear();
    }
}
