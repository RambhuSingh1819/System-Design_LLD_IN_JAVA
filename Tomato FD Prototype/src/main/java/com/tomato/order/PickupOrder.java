package com.tomato.order;

import com.tomato.models.Menu;
import com.tomato.models.Restaurant;
import com.tomato.models.User;
import com.tomato.strategy.IPaymentStrategy;
import java.util.List;

public class PickupOrder extends Order {
    private String restAddress;

    public PickupOrder(User user, Restaurant restaurant, List<Menu> items, IPaymentStrategy paymentStrategy, String restAddress) {
        super(user, restaurant, items, paymentStrategy);
        this.restAddress = restAddress;
    }

    public String getRestAddress() {
        return restAddress;
    }

    public void setRestAddress(String restAddress) {
        this.restAddress = restAddress;
    }

    @Override
    public String getType() {
        return "Pickup";
    }

    @Override
    public String toString() {
        return super.toString() + String.format("  Pickup Restaurant Address: %s%n", restAddress);
    }
}
