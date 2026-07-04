package com.tomato.factory;

import com.tomato.models.Menu;
import com.tomato.models.Restaurant;
import com.tomato.models.User;
import com.tomato.order.DeliverOrder;
import com.tomato.order.Order;
import com.tomato.order.PickupOrder;
import com.tomato.strategy.IPaymentStrategy;
import java.util.List;

public class ScheduleOrder implements IOrderFactory {
    private String time;

    public ScheduleOrder(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public Order createOrder(String type, User user, Restaurant restaurant, List<Menu> items, IPaymentStrategy strategy) {
        if (type == null) {
            throw new IllegalArgumentException("Order type cannot be null");
        }
        
        Order order;
        if (type.equalsIgnoreCase("delivery")) {
            order = new DeliverOrder(user, restaurant, items, strategy, user.getAddress());
        } else if (type.equalsIgnoreCase("pickup")) {
            order = new PickupOrder(user, restaurant, items, strategy, restaurant.getLocation());
        } else {
            throw new IllegalArgumentException("Unknown order type: " + type);
        }
        
        order.setScheduledTime(time);
        return order;
    }
}
