package com.example.demo.appointment.dto;

import com.example.demo.appointment.entity.Appointment.AppointmentStatus;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentResponse {

    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String symptoms;
    private AppointmentStatus status;
    private String notes;
    private String message;

    // constructors
    public AppointmentResponse() {}

    public AppointmentResponse(Long id, Long patientId, Long doctorId,
                                LocalDate appointmentDate, LocalTime appointmentTime,
                                String symptoms, AppointmentStatus status,
                                String notes, String message) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.symptoms = symptoms;
        this.status = status;
        this.notes = notes;
        this.message = message;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPatientId() { return patientId; }
    public void setPatientId(Long patientId) { this.patientId = patientId; }

    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate date) { this.appointmentDate = date; }

    public LocalTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalTime time) { this.appointmentTime = time; }

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    public AppointmentStatus getStatus() { return status; }
    public void setStatus(AppointmentStatus status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
