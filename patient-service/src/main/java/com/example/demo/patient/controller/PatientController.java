package com.example.demo.patient.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.patient.dto.LoginRequest;
import com.example.demo.patient.dto.LoginResponse;
import com.example.demo.patient.entity.Patient;
import com.example.demo.patient.service.AIService;
import com.example.demo.patient.service.PatientService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/patients")
public class PatientController {
	 @Autowired
	    private PatientService patientService;
	 
	 @Autowired
	 private AIService aiService; 

	 
	 @PostMapping("/check-symptoms")
	 public ResponseEntity<String> checkSymptoms(@RequestBody Map<String, String> body) {
	     String symptoms = body.get("symptoms");
	     String recommendation = aiService.checkSymptoms(symptoms);
	     return ResponseEntity.ok(recommendation);
	 }

	 // patient Q&A
	 @PostMapping("/ask")
	 public ResponseEntity<String> askQuestion(@RequestBody Map<String, String> body) {
	     String question = body.get("question");
	     String answer = aiService.askQuestion(question);
	     return ResponseEntity.ok(answer);
	 }
	 
	 
	 
	 
	    // register patient
	    @PostMapping("/register")
	    public ResponseEntity<Patient> register(@RequestBody Patient patient) {
	        Patient saved = patientService.registerPatient(patient);
	        return ResponseEntity.ok(saved);
	    }
	    
	    @PostMapping("/login")
	    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
	        String token = patientService.loginPatient(
	            loginRequest.getEmail(),
	            loginRequest.getPassword()
	        );
	        return ResponseEntity.ok(new LoginResponse(
	            token,
	            loginRequest.getEmail(),
	            "Login successful!"
	        ));
	    }
	    

	    // get all patients
	    @GetMapping
	    public ResponseEntity<List<Patient>> getAllPatients() {
	        return ResponseEntity.ok(patientService.getAllPatients());
	    }

	    // get patient by id
	    @GetMapping("/{id}")
	    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
	        return patientService.getPatientById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }
	    
	    // update patient
	    @PutMapping("/{id}")
	    public ResponseEntity<Patient> updatePatient(
	            @PathVariable Long id,
	            @RequestBody Patient patient) {
	        return ResponseEntity.ok(patientService.updatePatient(id, patient));
	    }

	    // delete patient
	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
	        patientService.deletePatient(id);
	        return ResponseEntity.ok("Patient deleted successfully!");
	    }
	
}


