package com.example.projectqlcv.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private String rePassword;
    private String address;
    private String avatar;

    public User() {
    }

    public User(String email) {
        this.email = email;
    }

    public User(String email, String rePassword) {
        this.email = email;
        this.rePassword = rePassword;
    }

    public User(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public User(String name, String email, String phoneNumber, String password, String address, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.avatar = avatar;
    }

    public User(int id, String name, String email, String phoneNumber, String password, String rePassword, String address, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.rePassword = rePassword;
        this.address = address;
        this.avatar = avatar;
    }

    public User(int id, String name, String email, String password, String address, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.avatar = avatar;
    }

    public User(int id, String name, String email, String phoneNumber, String password, String address, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.avatar = avatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
