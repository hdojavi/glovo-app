package com.jhernando.glovo.model;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private String orderdate;
    private int businessid;
    private Business business;
    private int userid;
    private int ordermethod;
    private String ordermethodname;
    private double pricetotal;

    public Order(int id, String orderDate, int businessId, int userId, int orderMethod, double priceTotal) {
        this.id = id;
        this.orderdate = orderDate;
        this.businessid = businessId;
        this.userid = userId;
        this.ordermethod = orderMethod;
        this.pricetotal = priceTotal;
    }

    public Order(int id, String orderDate, int businessId, Business business, int userId, int orderMethod, String orderMethodName, double priceTotal) {
        this.id = id;
        this.orderdate = orderDate;
        this.businessid = businessId;
        this.business = business;
        this.userid = userId;
        this.ordermethod = orderMethod;
        this.ordermethodname = orderMethodName;
        this.pricetotal = priceTotal;
    }

    public Order(int businessId, Business business, int userId, int orderMethod, String orderMethodName, double priceTotal) {
        this.businessid = businessId;
        this.business = business;
        this.userid = userId;
        this.ordermethod = orderMethod;
        this.ordermethodname = orderMethodName;
        this.pricetotal = priceTotal;
    }

    public Order(int id, double priceTotal) {
        this.id = id;
        this.pricetotal = priceTotal;
    }

    public Order() {
    }

    public Order(int id) {
        this.id = id;
    }

    public double getPriceTotal() {
        return pricetotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.pricetotal = priceTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderdate;
    }

    public void setOrderDate(String orderDate) {
        this.orderdate = orderDate;
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int userId) {
        this.userid = userId;
    }

    public int getOrderMethod() {
        return ordermethod;
    }

    public void setOrderMethod(int orderMethod) {
        this.ordermethod = orderMethod;
    }

    public int getBusinessId() {
        return businessid;
    }

    public void setBusinessId(int businessId) {
        this.businessid = businessId;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getOrderMethodName() {
        return ordermethodname;
    }

    public void setOrderMethodName(String orderMethodName) {
        this.ordermethodname = orderMethodName;
    }
}
