package com.tomato.service;

import com.tomato.order.Order;

public abstract class NotificationService {
    protected Order order;

    public NotificationService(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public abstract void notifyUser();
}
