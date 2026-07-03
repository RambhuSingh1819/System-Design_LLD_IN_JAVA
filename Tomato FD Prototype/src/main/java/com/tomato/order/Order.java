package com.tomato.order;

import com.tomato.models.Menu;
import com.tomato.models.Restaurant;
import com.tomato.models.User;
import com.tomato.strategy.IPaymentStrategy;
import java.util.List;

public abstract class Order {
    private static int idCounter = 1000;
    
    private final int id;
    private final User user;
    private final Restaurant restaurant;
    private final List<Menu> items;
    private final IPaymentStrategy paymentStrategy;
    private String scheduledTime; 
    public Order(User user, Restaurant restaurant, List<Menu> items, IPaymentStrategy paymentStrategy) {
        this.id = ++idCounter;
        this.user = user;
        this.restaurant = restaurant;
        this.items = items;
        this.paymentStrategy = paymentStrategy;
        this.scheduledTime = null; 
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Menu> getItems() {
        return items;
    }

    public IPaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
    
    public double getTotalAmount() {
        double total = 0;
        for (Menu item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public abstract String getType();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Order #%d [%s]%n", id, getType()));
        if (scheduledTime != null) {
            sb.append(String.format("  Scheduled Time: %s%n", scheduledTime));
        } else {
            sb.append("  Fulfillment: Immediate (Now)\n");
        }
        sb.append(String.format("  User: %s%n", user.getName()));
        sb.append(String.format("  Restaurant: %s%n", restaurant.getName()));
        sb.append("  Items:\n");
        for (Menu item : items) {
            sb.append(String.format("    - %s%n", item.toString()));
        }
        sb.append(String.format("  Total Paid: $%.2f%n", getTotalAmount()));
        return sb.toString();
    }
}
