package com.example.projectqlcv.model;

public class Table {
    private String id;
    private String name;
    private String permission;
    private String group;

    public Table( String name, String permission, String group) {
        this.name = name;
        this.permission = permission;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}