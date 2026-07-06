package com.tomato.singleton;

import com.tomato.order.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;
    private List<Order> orderList;

    private OrderManager() {
        orderList = new ArrayList<>();
    }

    public static OrderManager getInstance() {
        if (instance == null) {
            synchronized (OrderManager.class) {
                if (instance == null) {
                    instance = new OrderManager();
                }
            }
        }
        return instance;
    }

    public void addOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Cannot add null order");
        }
        orderList.add(order);
    }

    public List<Order> listOrder() {
        return new ArrayList<>(orderList); 
    }

    public void clear() {
        orderList.clear();
    }
}
