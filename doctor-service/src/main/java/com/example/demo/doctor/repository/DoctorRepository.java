package com.example.demo.doctor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.doctor.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	
	 Optional<Doctor> findByEmail(String email);
	    boolean existsByEmail(String email);
	    List<Doctor> findBySpecialization(String specialization);
	    List<Doctor> findByAvailable(boolean available);
	    List<Doctor> findBySpecializationAndAvailable(String specialization, boolean available);

}
