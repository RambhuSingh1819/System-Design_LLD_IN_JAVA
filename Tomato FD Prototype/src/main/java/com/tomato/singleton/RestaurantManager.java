package com.tomato.singleton;

import com.tomato.models.Restaurant;
import java.util.ArrayList;
import java.util.List;

public class RestaurantManager {
    private static RestaurantManager instance;
    private List<Restaurant> restaurants;

    private RestaurantManager() {
        restaurants = new ArrayList<>();
    }
    public static RestaurantManager getInstance() {
        if (instance == null) {
            synchronized (RestaurantManager.class) {
                if (instance == null) {
                    instance = new RestaurantManager();
                }
            }
        }
        return instance;
    }

    public void addRestaurant(Restaurant r) {
        if (r == null) {
            throw new IllegalArgumentException("Cannot add null restaurant");
        }
        restaurants.add(r);
    }

    public List<Restaurant> getRestaurants() {
        return new ArrayList<>(restaurants);
    }

    public List<Restaurant> searchByAddress(String locationQuery) {
        List<Restaurant> results = new ArrayList<>();
        if (locationQuery == null || locationQuery.trim().isEmpty()) {
            return results;
        }
        for (Restaurant r : restaurants) {
            if (r.getLocation().toLowerCase().contains(locationQuery.toLowerCase())) {
                results.add(r);
            }
        }
        return results;
    }
    
    public void clear() {
        restaurants.clear();
    }
}
