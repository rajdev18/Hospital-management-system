package com.example.demo.medical.service;

import com.example.demo.medical.dto.MedicalRecordRequest;
import com.example.demo.medical.dto.MedicalRecordResponse;
import com.example.demo.medical.entity.MedicalRecord.RecordStatus;
import java.util.List;

public interface MedicalRecordService {

    // create medical record
	
	MedicalRecordResponse createRecord(
		    MedicalRecordRequest request, String token);
	


    // get all records
    List<MedicalRecordResponse> getAllRecords();

    // get record by id
    MedicalRecordResponse getRecordById(Long id);

    // get records by patient
    List<MedicalRecordResponse> getRecordsByPatient(Long patientId);

    // get records by doctor
    List<MedicalRecordResponse> getRecordsByDoctor(Long doctorId);

    // get records by appointment
    List<MedicalRecordResponse> getRecordsByAppointment(Long appointmentId);

    // update record
    MedicalRecordResponse updateRecord(Long id, MedicalRecordRequest request);

    // update discharge summary
    MedicalRecordResponse updateDischargeSummary(Long id, String dischargeSummary);

    // update status
    MedicalRecordResponse updateStatus(Long id, RecordStatus status);

    // delete record
    void deleteRecord(Long id);
}