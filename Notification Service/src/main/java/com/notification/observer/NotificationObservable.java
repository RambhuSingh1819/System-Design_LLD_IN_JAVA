package com.notification.observer;

import com.notification.Notification;
import java.util.ArrayList;
import java.util.List;

public class NotificationObservable implements Observable {
    private final List<Observer> observers = new ArrayList<>();
    private Notification notification;

    @Override
    public void add(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
        notifyObservers();
    }

    public Notification getNotification() {
        return this.notification;
    }
}
