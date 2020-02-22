package com.jhernando.glovo.model;

public class Category {

    private int id;
    private String category;
    private String thumb;

    public Category() {
    }

    public Category(int id) {
        this.id = id;
    }

    public Category(String category) {
        this.category = category;
    }

    public Category(int id, String category, String thumb) {
        this.id = id;
        this.category = category;
        this.thumb = thumb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
