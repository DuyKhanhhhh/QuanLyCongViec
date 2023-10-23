package com.example.projectqlcv.model;

public class Group {
    private int id;
    private String name;
    private String groupType;
    private String permission;
    private String  information;

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

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Group() {
    }

    public Group(String name, String groupType, String permission, String information) {
        this.name = name;
        this.groupType = groupType;
        this.permission = permission;
        this.information = information;
    }

    public Group(int id, String name, String groupType, String permission, String information) {
        this.id = id;
        this.name = name;
        this.groupType = groupType;
        this.permission = permission;
        this.information = information;
    }
}