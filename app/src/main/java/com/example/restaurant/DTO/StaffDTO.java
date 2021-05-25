package com.example.restaurant.DTO;

public class StaffDTO {
    int id;
    String username, password,sex, dateOfBirth, iden, number, fullName, avatar;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public StaffDTO() {
    }

    public StaffDTO(String username, String password, String sex, String dateOfBirth, String iden, String number, String fullName, String avatar) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.iden = iden;
        this.number = number;
        this.fullName = fullName;
        this.avatar = avatar;
    }

    public StaffDTO(String username, String password, String sex, String dateOfBirth, String iden) {
        this.iden = iden;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIden() {
        return iden;
    }

    public void setIden(String iden) {
        this.iden = iden;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
