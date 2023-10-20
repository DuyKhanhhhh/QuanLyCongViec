package com.example.projectqlcv.model;

public class LoginUser {
    private int id;
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private String rePassword;

    public LoginUser(int id, String email, String name, String phoneNumber, String password, String rePassword) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.rePassword = rePassword;
    }

    public LoginUser(String email, String rePassword) {
        this.email = email;
        this.rePassword = rePassword;
    }

    public LoginUser() {
    }
    public LoginUser(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public LoginUser(int id, String email, String name, String phoneNumber, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
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
}
