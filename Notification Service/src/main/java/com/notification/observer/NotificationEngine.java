package com.notification.observer;

import com.notification.strategy.NotificationStrategy;
import java.util.ArrayList;
import java.util.List;

public class NotificationEngine implements Observer {
    private final NotificationObservable observable;
    private final List<NotificationStrategy> strategies = new ArrayList<>();

    public NotificationEngine(NotificationObservable observable) {
        this.observable = observable;
    }

    public void addStrategy(NotificationStrategy strategy) {
        if (strategy != null && !strategies.contains(strategy)) {
            strategies.add(strategy);
        }
    }

    public void removeStrategy(NotificationStrategy strategy) {
        strategies.remove(strategy);
    }

    public List<NotificationStrategy> getStrategies() {
        return new ArrayList<>(strategies);
    }

    @Override
    public void update() {
        if (observable != null && observable.getNotification() != null) {
            String content = observable.getNotification().getContent();
            System.out.println("\u001B[33m" + "[ENGINE] Routing notification to " + strategies.size() + " active strategies..." + "\u001B[0m");
            for (NotificationStrategy strategy : strategies) {
                strategy.sendNotification(content);
            }
        }
    }
}
