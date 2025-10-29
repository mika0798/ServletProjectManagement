package com.project.entity;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private int role_id;
    private Role role;

    public User(int id, String name, String password, String email, String phone, String address, int role_id, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role_id = role_id;
        this.role = role;
    }

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getAddress() {
        return this.address;
    }

    public int getRole_id() {
        return this.role_id;
    }

    public Role getRole() {
        return this.role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}