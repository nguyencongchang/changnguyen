package com.example.restaurant.DTO;

public class DeskDTO {
    int id;
    String  name;
    boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public DeskDTO() {
    }

    public DeskDTO(String name, boolean selected) {
        this.name = name;
        this.selected = selected;
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

}
