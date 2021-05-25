package com.example.restaurant.DTO;

public class DishDTO {
    private int id, category, price;
    private String name, imageUrl;

    public DishDTO() {
    }
    public DishDTO(String name, int price, int category, String imageUrl) {
        this.category = category;
        this.price = price;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
