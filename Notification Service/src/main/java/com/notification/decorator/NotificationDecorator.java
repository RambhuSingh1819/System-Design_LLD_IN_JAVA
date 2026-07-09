package com.notification.decorator;

import com.notification.Notification;

public abstract class NotificationDecorator implements Notification {
    protected final Notification notification;

    public NotificationDecorator(Notification notification) {
        this.notification = notification;
    }
  
    @Override
    public String getContent() {
        return notification.getContent();
    }
}
