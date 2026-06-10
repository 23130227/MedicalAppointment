package com.example.medicalappointment.data.model;

public class Doctor {
    private String id;
    private String accountId;
    private String specialtyId;
    private String fullname;
    private String bio;
    private int consultationFee;

    public Doctor() {}

    public Doctor(String id, String accountId, String specialtyId, String fullname, String bio, int consultationFee) {
        this.id = id;
        this.accountId = accountId;
        this.specialtyId = specialtyId;
        this.fullname = fullname;
        this.bio = bio;
        this.consultationFee = consultationFee;
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

    public String getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(String specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(int consultationFee) {
        this.consultationFee = consultationFee;
    }
}
