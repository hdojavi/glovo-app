package com.jhernando.glovo.model;

public class OrderLine {
    private int id;
    private int orderid;
    private int productid;
    private String productname;
    private int quantity;
    private double price;

    public OrderLine() {
    }

    public OrderLine(int orderId) {
        this.orderid = orderId;
    }

    public OrderLine(int id, int orderId, int productId, int quantity, double price) {
        this.id = id;
        this.orderid = orderId;
        this.productid = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderLine(int id, int orderId, int productId, String productName, int quantity, double price) {
        this.id = id;
        this.orderid = orderId;
        this.productid = productId;
        this.productname = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId() {
        return orderid;
    }

    public void setOrderId(int orderId) {
        this.orderid = orderId;
    }

    public int getProductId() {
        return productid;
    }

    public void setProductId(int productId) {
        this.productid = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productname;
    }

    public void setProductName(String productName) {
        this.productname = productName;
    }
}
