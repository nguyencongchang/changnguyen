package com.example.restaurant.DTO;

public class OrderDetailDTO {
    private int orderId, dishId, amount;

    public OrderDetailDTO(int orderId, int dishId, int amount) {
        this.orderId = orderId;
        this.dishId = dishId;
        this.amount = amount;
    }

    public OrderDetailDTO() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
