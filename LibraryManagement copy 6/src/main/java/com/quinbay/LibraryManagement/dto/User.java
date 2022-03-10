package com.quinbay.LibraryManagement.dto;

public class User {
    private Long id;
    private String name;
    private String address;
    private String phoneNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public User(Long id, String name, String address, String phoneNum) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
    }
    public User(){
    }
}
