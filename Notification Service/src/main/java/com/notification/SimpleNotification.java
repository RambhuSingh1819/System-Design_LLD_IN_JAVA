package com.notification;

public class SimpleNotification implements Notification {
    private final String text;

    public SimpleNotification(String text) {
        this.text = text;
    }
    @Override
    public String getContent() {
        return text;
    }
}
