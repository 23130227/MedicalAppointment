package com.example.medicalappointment.data.model;

public class Appointment {
    private String id;
    private String patientId;
    private String doctorScheduleId;
    private int actualPrice;
    private String symptoms;
    private String status;
    private String paymentStatus;
    private String createdAt;
    private String updatedAt;

    public Appointment() {}

    public Appointment(String id, String patientId, String doctorScheduleId, int actualPrice,
                       String symptoms, String status, String paymentStatus, String createdAt, String updatedAt) {
        this.id = id;
        this.patientId = patientId;
        this.doctorScheduleId = doctorScheduleId;
        this.actualPrice = actualPrice;
        this.symptoms = symptoms;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorScheduleId() {
        return doctorScheduleId;
    }

    public void setDoctorScheduleId(String doctorScheduleId) {
        this.doctorScheduleId = doctorScheduleId;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
