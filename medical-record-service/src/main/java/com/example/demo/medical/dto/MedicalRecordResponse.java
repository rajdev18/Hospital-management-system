package com.example.demo.medical.dto;

import java.time.LocalDate;

import com.example.demo.medical.entity.MedicalRecord.RecordStatus;

public class MedicalRecordResponse {
	 private Long id;
	    private Long patientId;
	    private Long doctorId;
	    private Long appointmentId;
	    private String diagnosis;
	    private String prescription;
	    private String symptoms;
	    private String notes;
	    private String dischargeSummary;
	    private LocalDate recordDate;
	    private RecordStatus status;
	    private String message;
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
		public Long getAppointmentId() {
			return appointmentId;
		}
		public void setAppointmentId(Long appointmentId) {
			this.appointmentId = appointmentId;
		}
		public String getDiagnosis() {
			return diagnosis;
		}
		public void setDiagnosis(String diagnosis) {
			this.diagnosis = diagnosis;
		}
		public String getPrescription() {
			return prescription;
		}
		public void setPrescription(String prescription) {
			this.prescription = prescription;
		}
		public String getSymptoms() {
			return symptoms;
		}
		public void setSymptoms(String symptoms) {
			this.symptoms = symptoms;
		}
		public String getNotes() {
			return notes;
		}
		public void setNotes(String notes) {
			this.notes = notes;
		}
		public String getDischargeSummary() {
			return dischargeSummary;
		}
		public void setDischargeSummary(String dischargeSummary) {
			this.dischargeSummary = dischargeSummary;
		}
		public LocalDate getRecordDate() {
			return recordDate;
		}
		public void setRecordDate(LocalDate recordDate) {
			this.recordDate = recordDate;
		}
		public RecordStatus getStatus() {
			return status;
		}
		public void setStatus(RecordStatus status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public MedicalRecordResponse(Long id, Long patientId, Long doctorId, Long appointmentId, String diagnosis,
				String prescription, String symptoms, String notes, String dischargeSummary, LocalDate recordDate,
				RecordStatus status, String message) {
			super();
			this.id = id;
			this.patientId = patientId;
			this.doctorId = doctorId;
			this.appointmentId = appointmentId;
			this.diagnosis = diagnosis;
			this.prescription = prescription;
			this.symptoms = symptoms;
			this.notes = notes;
			this.dischargeSummary = dischargeSummary;
			this.recordDate = recordDate;
			this.status = status;
			this.message = message;
		}
	    
	    
	    
	    
}
