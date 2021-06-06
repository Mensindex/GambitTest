package com.example.gambittest;

public class DishModel {
    private String id;
    private String name;
    private String image;
    private int price;
    private String description;
    private int count;


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

    public DishModel(String id, String name, String image, int price, String description, int count) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.count = count;
    }
}
