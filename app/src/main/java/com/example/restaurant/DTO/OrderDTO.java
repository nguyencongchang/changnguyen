package com.example.restaurant.DTO;

public class OrderDTO {
    private int orderId, deskId, staffId;
    private String status, date;

    public OrderDTO() {
    }

    public OrderDTO(int deskId, int staffId, String status, String date) {
        this.deskId = deskId;
        this.staffId = staffId;
        this.status = status;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDeskId() {
        return deskId;
    }

    public void setDeskId(int deskId) {
        this.deskId = deskId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
