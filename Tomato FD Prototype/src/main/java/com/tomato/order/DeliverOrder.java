package com.tomato.order;

import com.tomato.models.Menu;
import com.tomato.models.Restaurant;
import com.tomato.models.User;
import com.tomato.strategy.IPaymentStrategy;
import java.util.List;


public class DeliverOrder extends Order {
    private String address;

    public DeliverOrder(User user, Restaurant restaurant, List<Menu> items, IPaymentStrategy paymentStrategy,
            String address) {
        super(user, restaurant, items, paymentStrategy);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getType() {
        return "Delivery";
    }

    @Override
    public String toString() {
        return super.toString() + String.format("  Delivery Address: %s%n", address);
    }
}
