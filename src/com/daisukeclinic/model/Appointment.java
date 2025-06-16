package com.daisukeclinic.model;

public class Appointment {
    int patientId;
    String patientName; 
    String time; // Format: "HH:mm"
    String doctorId;
    

    public Appointment(int patientId, String patientName,String time, String doctorId) {
        this.patientId = patientId;
        this.patientName = patientName; 
        this.time = time;
        this.doctorId = doctorId;
    }

    public int getTimeInMinutes() {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public String toString() {
        return "Patient: " + patientId + "| Patient Name: " + patientName + "| Time: " + time + "| Doctor ID: " + doctorId;
    } 
}