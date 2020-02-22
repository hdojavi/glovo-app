package com.jhernando.glovo.model;

import java.io.Serializable;

public class Business implements Serializable {
    private int id;
    private String name;
    private String description;
    private double shippingprice;
    private int rate;
    private int kmaway;
    private int businesscategoryid;
    private String thumb;

    public Business() {
    }

    public Business(int id) {
        this.id = id;
    }

    public Business(String name) {
        this.name = name;
    }

    public Business(int id, int businessCategoryId) {
        this.id = id;
        this.businesscategoryid = businessCategoryId;
    }

    public Business(int id, String name, String description, double shippingPrice, int rate, int kmAway, int businessCategoryId, String Thumb) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shippingprice = shippingPrice;
        this.rate = rate;
        this.kmaway = kmAway;
        this.businesscategoryid = businessCategoryId;
        this.thumb = Thumb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getShippingPrice() {
        return shippingprice;
    }

    public void setShippingPrice(double shippingPrice) {
        this.shippingprice = shippingPrice;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getKmAway() {
        return kmaway;
    }

    public void setKmAway(int kmAway) {
        this.kmaway = kmAway;
    }

    public int getBusinessCategoryId() {
        return businesscategoryid;
    }

    public void setBusinessCategoryId(int businessCategoryId) {
        this.businesscategoryid = businessCategoryId;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String Thumb) {
        this.thumb = Thumb;
    }
}
