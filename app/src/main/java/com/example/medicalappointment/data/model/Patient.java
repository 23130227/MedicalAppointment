package com.example.medicalappointment.data.model;

public class Patient {
    private String id;
    private String accountId;
    private String fullname;
    private String dob;
    private String gender;
    private String relationship;
    private String identityCard;
    private String phone;

    public Patient() {}

    public Patient(String id, String accountId, String fullname, String dob, String gender,
                   String relationship, String identityCard, String phone) {
        this.id = id;
        this.accountId = accountId;
        this.fullname = fullname;
        this.dob = dob;
        this.gender = gender;
        this.relationship = relationship;
        this.identityCard = identityCard;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
