package com.tomato.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Restaurant restaurant;
    private List<Menu> items;

    public Cart() {
        this.items = new ArrayList<>();
        this.restaurant = null;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Menu> getItems() {
        return new ArrayList<>(items); 
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double countTotal() {
        double total = 0;
        for (Menu item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void addItem(Menu item, Restaurant r) {
        if (item == null || r == null) {
            throw new IllegalArgumentException("Item and Restaurant cannot be null");
        }
        if (this.restaurant != null && this.restaurant.getRestId() != r.getRestId()) {
            throw new IllegalStateException("Cannot add items from a different restaurant. Clear cart first.");
        }
        if (this.restaurant == null) {
            this.restaurant = r;
        }
        this.items.add(item);
    }

    public void removeItem(Menu item) {
        if (item == null)
            return;
        this.items.remove(item);
        if (this.items.isEmpty()) {
            this.restaurant = null;
        }
    }

    public void clear() {
        this.items.clear();
        this.restaurant = null;
    }
}
