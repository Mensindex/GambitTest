package com.example.gambittest;

public class DishModel {
    private String id;
    private String name;
    private String image;
    private int price;
    private String description;
    private int count;
    private boolean isFavorite;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public DishModel(String id, String name, String image, int price, String description, int count, boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.count = count;
        this.isFavorite = isFavorite;
    }
}
