package com.example.demo.patient.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.patient.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	 Optional<Patient> findByEmail(String email);
	    boolean existsByEmail(String email);

}
