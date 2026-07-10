package com.notification.observer;

public class Logger implements Observer {
    private final NotificationObservable observable;

    public Logger(NotificationObservable observable) {
        this.observable = observable;
    }
    @Override
    public void update() {
        if (observable != null && observable.getNotification() != null) {
            String content = observable.getNotification().getContent();
            System.out.println("\u001B[36m" + "[LOG AUDIT] Notification processed and saved in audit logs." + "\u001B[0m");
            System.out.println("----------------------------------------");
            System.out.println(content);
            System.out.println("----------------------------------------");
        }
    }
}
