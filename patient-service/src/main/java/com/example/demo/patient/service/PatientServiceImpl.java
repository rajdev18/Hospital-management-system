package com.example.demo.patient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.patient.entity.Patient;
import com.example.demo.patient.repository.PatientRepository;
import com.example.demo.patient.security.JwtUtil;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String loginPatient(String email, String password) {
	    Patient patient = patientRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("Patient not found!"));

	    if (!passwordEncoder.matches(password, patient.getPassword())) {
	        throw new RuntimeException("Invalid password!");
	    }

	    return jwtUtil.generateToken(email);
	}
	
	
	
	@Autowired
	 
	    private PatientRepository patientRepository;

	    @Override
	    public Patient registerPatient(Patient patient) {
	        if (patientRepository.existsByEmail(patient.getEmail())) {
	            throw new RuntimeException("Email already registered!");
	        }
	        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
	        return patientRepository.save(patient);
	    }

	    @Override
	    public List<Patient> getAllPatients() {
	        return patientRepository.findAll();
	    }
	    @Override
	    public Optional<Patient> getPatientById(Long id) {
	        return patientRepository.findById(id);
	    }

	    @Override
	    public Optional<Patient> getPatientByEmail(String email) {
	        return patientRepository.findByEmail(email);
	    }

	    @Override
	    public Patient updatePatient(Long id, Patient updatedPatient) {
	        Patient existing = patientRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Patient not found!"));
	        existing.setName(updatedPatient.getName());
	        existing.setPhone(updatedPatient.getPhone());
	        existing.setAge(updatedPatient.getAge());
	        existing.setBloodGroup(updatedPatient.getBloodGroup());
	        existing.setAddress(updatedPatient.getAddress());
	        return patientRepository.save(existing);
	    }

	    @Override
	    public void deletePatient(Long id) {
	        patientRepository.deleteById(id);
	    }
}
