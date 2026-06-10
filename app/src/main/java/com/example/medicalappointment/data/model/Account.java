package com.example.medicalappointment.data.model;

public class Account {
    private String id;
    private String phone;
    private String email;
    private String role;
    private String createdAt;
    public Account() {}
    public Account(String id, String phone, String email, String role, String createdAt) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }
    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
