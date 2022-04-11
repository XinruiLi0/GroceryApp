package com.example.groceryapp.OrderHelper;

public class OrderHelperClass {
    private String id;
    private String orderNumber;
    private String orderDate;
    private String name;
    private String phoneNumber;

    public OrderHelperClass(String id, String orderNumber, String orderDate, String name, String phoneNumber) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
