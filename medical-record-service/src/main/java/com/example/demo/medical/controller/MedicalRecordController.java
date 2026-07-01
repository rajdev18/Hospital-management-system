package com.example.demo.medical.controller;

import com.example.demo.medical.entity.MedicalRecord;
import com.example.demo.medical.dto.MedicalRecordRequest;
import com.example.demo.medical.dto.MedicalRecordResponse;
import com.example.demo.medical.entity.MedicalRecord.RecordStatus;
import com.example.demo.medical.service.AIService;
import com.example.demo.medical.service.MedicalRecordService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medical-records")
@SecurityRequirement(name = "bearerAuth")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;
    
    
    @Autowired
    private AIService aiService;

    
    @PostMapping("/{id}/generate-discharge-summary")
    public ResponseEntity<String> generateDischargeSummary(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        // get existing record
        MedicalRecordResponse record = medicalRecordService.getRecordById(id);

        // generate AI discharge summary
        String summary = aiService.generateDischargeSummary(
            body.get("patient_name"),
            record.getDiagnosis(),
            record.getPrescription(),
            record.getSymptoms(),
            record.getNotes(),
            body.get("doctor_name")
        );

        // save discharge summary to record
        medicalRecordService.updateDischargeSummary(id, summary);
        
        medicalRecordService.updateStatus(id, MedicalRecord.RecordStatus.ARCHIVED);

        return ResponseEntity.ok(summary);
    }
    

    // create record
    @PostMapping
    public ResponseEntity<MedicalRecordResponse> createRecord(
            @RequestBody MedicalRecordRequest request,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(
            medicalRecordService.createRecord(request, token));
    }
    

//    @PostMapping
//    public ResponseEntity<MedicalRecordResponse> createRecord(
//            @RequestBody MedicalRecordRequest request) {
//        return ResponseEntity.ok(medicalRecordService.createRecord(request));
//    }

    // get all records
    @GetMapping
    public ResponseEntity<List<MedicalRecordResponse>> getAllRecords() {
        return ResponseEntity.ok(medicalRecordService.getAllRecords());
    }

    // get record by id
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordResponse> getRecordById(
            @PathVariable Long id) {
        return ResponseEntity.ok(medicalRecordService.getRecordById(id));
    }

    // get records by patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalRecordResponse>> getByPatient(
            @PathVariable Long patientId) {
        return ResponseEntity.ok(
            medicalRecordService.getRecordsByPatient(patientId));
    }

    // get records by doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<MedicalRecordResponse>> getByDoctor(
            @PathVariable Long doctorId) {
        return ResponseEntity.ok(
            medicalRecordService.getRecordsByDoctor(doctorId));
    }

    // get records by appointment
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<MedicalRecordResponse>> getByAppointment(
            @PathVariable Long appointmentId) {
        return ResponseEntity.ok(
            medicalRecordService.getRecordsByAppointment(appointmentId));
    }

    // update record
    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordResponse> updateRecord(
            @PathVariable Long id,
            @RequestBody MedicalRecordRequest request) {
        return ResponseEntity.ok(medicalRecordService.updateRecord(id, request));
    }

    // update discharge summary
    @PutMapping("/{id}/discharge-summary")
    public ResponseEntity<MedicalRecordResponse> updateDischargeSummary(
            @PathVariable Long id,
            @RequestParam String dischargeSummary) {
        return ResponseEntity.ok(
            medicalRecordService.updateDischargeSummary(id, dischargeSummary));
    }

    // update status
    @PutMapping("/{id}/status")
    public ResponseEntity<MedicalRecordResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam RecordStatus status) {
        return ResponseEntity.ok(medicalRecordService.updateStatus(id, status));
    }

    // delete record
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id) {
        medicalRecordService.deleteRecord(id);
        return ResponseEntity.ok("Medical record deleted successfully!");
    }
}
