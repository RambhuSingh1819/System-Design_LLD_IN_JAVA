package com.tomato.service;

import com.tomato.order.Order;

public interface INotificationChannel {

    void send(Order order);
}
