package com.example.demo.patient.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.patient.entity.Patient;

public interface PatientService {
	
	// add this to PatientService interface
	String loginPatient(String email, String password);
	
	
	 Patient registerPatient(Patient patient);
	    List<Patient> getAllPatients();
	    Optional<Patient> getPatientById(Long id);
	    Optional<Patient> getPatientByEmail(String email);
	    Patient updatePatient(Long id, Patient updatedPatient);
	    void deletePatient(Long id);
}
