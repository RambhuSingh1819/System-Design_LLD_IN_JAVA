package com.tomato.models;

public class User {
    private int uid;
    private String name;
    private String address;
    private Cart cart; 

    public User(int uid, String name, String address) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.cart = new Cart(); 
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return String.format("User ID: %d, Name: %s, Address: %s", uid, name, address);
    }
}
