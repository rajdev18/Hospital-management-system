package com.example.demo.doctor.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.doctor.entity.Doctor;

public interface DoctorService {
	 Doctor registerDoctor(Doctor doctor);
	    String loginDoctor(String email, String password);
	    List<Doctor> getAllDoctors();
	    Optional<Doctor> getDoctorById(Long id);
	    Optional<Doctor> getDoctorByEmail(String email);
	    List<Doctor> getDoctorsBySpecialization(String specialization);
	    List<Doctor> getAvailableDoctors();
	    List<Doctor> getAvailableDoctorsBySpecialization(String specialization);
	    Doctor updateDoctor(Long id, Doctor doctor);
	    void deleteDoctor(Long id);
	    Doctor toggleAvailability(Long id);
}
