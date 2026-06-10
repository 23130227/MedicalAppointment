package com.example.medicalappointment.data.model;

public class DoctorSchedule {
    private String id;
    private String doctorId;
    private String date;
    private String timeSlot;
    private int maxPatients;
    private int currentPatients;
    private String status;

    public DoctorSchedule() {}

    public DoctorSchedule(String id, String doctorId, String date, String timeSlot,
                          int maxPatients, int currentPatients, String status) {
        this.id = id;
        this.doctorId = doctorId;
        this.date = date;
        this.timeSlot = timeSlot;
        this.maxPatients = maxPatients;
        this.currentPatients = currentPatients;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getMaxPatients() {
        return maxPatients;
    }

    public void setMaxPatients(int maxPatients) {
        this.maxPatients = maxPatients;
    }

    public int getCurrentPatients() {
        return currentPatients;
    }

    public void setCurrentPatients(int currentPatients) {
        this.currentPatients = currentPatients;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
