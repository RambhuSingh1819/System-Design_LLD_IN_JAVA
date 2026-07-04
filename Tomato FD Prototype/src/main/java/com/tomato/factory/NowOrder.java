package com.tomato.factory;

import com.tomato.models.Menu;
import com.tomato.models.Restaurant;
import com.tomato.models.User;
import com.tomato.order.DeliverOrder;
import com.tomato.order.Order;
import com.tomato.order.PickupOrder;
import com.tomato.strategy.IPaymentStrategy;
import java.util.List;

public class NowOrder implements IOrderFactory {

    @Override
    public Order createOrder(String type, User user, Restaurant restaurant, List<Menu> items, IPaymentStrategy strategy) {
        if (type == null) {
            throw new IllegalArgumentException("Order type cannot be null");
        }
        
        if (type.equalsIgnoreCase("delivery")) {
            return new DeliverOrder(user, restaurant, items, strategy, user.getAddress());
        } else if (type.equalsIgnoreCase("pickup")) {
            return new PickupOrder(user, restaurant, items, strategy, restaurant.getLocation());
        } else {
            throw new IllegalArgumentException("Unknown order type: " + type);
        }
    }
}
