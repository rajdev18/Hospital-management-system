package com.example.demo.appointment.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "appointments")
public class Appointment {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @NotNull(message = "Patient ID is required")
	    private Long patientId;

	    @NotNull(message = "Doctor ID is required")
	    private Long doctorId;

	    @NotNull(message = "Date is required")
	    private LocalDate appointmentDate;

	    @NotNull(message = "Time is required")
	    private LocalTime appointmentTime;

	    @NotBlank(message = "Symptoms are required")
	    private String symptoms;

	    @Enumerated(EnumType.STRING)
	    private AppointmentStatus status = AppointmentStatus.PENDING;

	    private String notes;
	    
	    public enum AppointmentStatus {
	        PENDING,
	        CONFIRMED,
	        COMPLETED,
	        CANCELLED
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getPatientId() {
			return patientId;
		}

		public void setPatientId(Long patientId) {
			this.patientId = patientId;
		}

		public Long getDoctorId() {
			return doctorId;
		}

		public void setDoctorId(Long doctorId) {
			this.doctorId = doctorId;
		}

		public LocalDate getAppointmentDate() {
			return appointmentDate;
		}

		public void setAppointmentDate(LocalDate appointmentDate) {
			this.appointmentDate = appointmentDate;
		}

		public LocalTime getAppointmentTime() {
			return appointmentTime;
		}

		public void setAppointmentTime(LocalTime appointmentTime) {
			this.appointmentTime = appointmentTime;
		}

		public String getSymptoms() {
			return symptoms;
		}

		public void setSymptoms(String symptoms) {
			this.symptoms = symptoms;
		}

		public AppointmentStatus getStatus() {
			return status;
		}

		public void setStatus(AppointmentStatus status) {
			this.status = status;
		}

		public String getNotes() {
			return notes;
		}

		public void setNotes(String notes) {
			this.notes = notes;
		}

		public Appointment(Long id, @NotNull(message = "Patient ID is required") Long patientId,
				@NotNull(message = "Doctor ID is required") Long doctorId,
				@NotNull(message = "Date is required") LocalDate appointmentDate,
				@NotNull(message = "Time is required") LocalTime appointmentTime,
				@NotBlank(message = "Symptoms are required") String symptoms, AppointmentStatus status, String notes) {
			super();
			this.id = id;
			this.patientId = patientId;
			this.doctorId = doctorId;
			this.appointmentDate = appointmentDate;
			this.appointmentTime = appointmentTime;
			this.symptoms = symptoms;
			this.status = status;
			this.notes = notes;
		}
	
	    public Appointment() {
	    	
	    }
	
}
