package com.tomato.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int restId;
    private String name;
    private String location;
    private List<Menu> menu;

    public Restaurant(int restId, String name, String location) {
        this.restId = restId;
        this.name = name;
        this.location = location;
        this.menu = new ArrayList<>();
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Menu> getMenu() {
        return new ArrayList<>(menu); 
    }

    public void addMenuItem(Menu item) {
        if (item == null) {
            throw new IllegalArgumentException("Menu item cannot be null");
        }
        this.menu.add(item);
    }

    public void removeMenuItem(String code) {
        this.menu.removeIf(item -> item.getCode().equalsIgnoreCase(code));
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s)", restId, name, location);
    }
}
