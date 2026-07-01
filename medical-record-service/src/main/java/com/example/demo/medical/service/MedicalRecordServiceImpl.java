package com.example.demo.medical.service;

import com.example.demo.medical.dto.MedicalRecordRequest;
import com.example.demo.medical.dto.MedicalRecordResponse;
import com.example.demo.medical.entity.MedicalRecord;
import com.example.demo.medical.entity.MedicalRecord.RecordStatus;
import com.example.demo.medical.feign.AppointmentClient;
import com.example.demo.medical.feign.DoctorClient;
import com.example.demo.medical.feign.PatientClient;
import com.example.demo.medical.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PatientClient patientClient;

    @Autowired
    private DoctorClient doctorClient;

    @Autowired
    private AppointmentClient appointmentClient;
    
    
    
  

    // convert entity to response
    private MedicalRecordResponse toResponse(MedicalRecord r) {
        return new MedicalRecordResponse(
            r.getId(), r.getPatientId(), r.getDoctorId(),
            r.getAppointmentId(), r.getDiagnosis(), r.getPrescription(),
            r.getSymptoms(), r.getNotes(), r.getDischargeSummary(),
            r.getRecordDate(), r.getStatus(), "Success"
        );
    }
    
    
    
    
    @Override
    public MedicalRecordResponse createRecord(
            MedicalRecordRequest request, String token) {

        // verify patient exists
        Object patient = patientClient.getPatientById(
            request.getPatientId(), token);
        if (patient == null) {
            throw new RuntimeException("Patient not found!");
        }

        // verify doctor exists
        Object doctor = doctorClient.getDoctorById(
            request.getDoctorId(), token);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found!");
        }

        // verify appointment exists
        Object appointment = appointmentClient.getAppointmentById(
            request.getAppointmentId(), token);
        if (appointment == null) {
            throw new RuntimeException("Appointment not found!");
        }

        // create record
        MedicalRecord record = new MedicalRecord();
        record.setPatientId(request.getPatientId());
        record.setDoctorId(request.getDoctorId());
        record.setAppointmentId(request.getAppointmentId());
        record.setDiagnosis(request.getDiagnosis());
        record.setPrescription(request.getPrescription());
        record.setSymptoms(request.getSymptoms());
        record.setNotes(request.getNotes());
        record.setRecordDate(LocalDate.now());
        record.setStatus(RecordStatus.ACTIVE);

        MedicalRecord saved = medicalRecordRepository.save(record);
        return toResponse(saved);
    }

//    @Override
//    public MedicalRecordResponse createRecord(MedicalRecordRequest request) {
//        // verify patient exists
//        Object patient = patientClient.getPatientById(request.getPatientId());
//        if (patient == null) {
//            throw new RuntimeException("Patient not found!");
//        }
//
//        // verify doctor exists
//        Object doctor = doctorClient.getDoctorById(request.getDoctorId());
//        if (doctor == null) {
//            throw new RuntimeException("Doctor not found!");
//        }
//        
//        
//
//        // verify appointment exists
//        Object appointment = appointmentClient.getAppointmentById(
//            request.getAppointmentId());
//        if (appointment == null) {
//            throw new RuntimeException("Appointment not found!");
//        }
//
//        // create record
//        MedicalRecord record = new MedicalRecord();
//        record.setPatientId(request.getPatientId());
//        record.setDoctorId(request.getDoctorId());
//        record.setAppointmentId(request.getAppointmentId());
//        record.setDiagnosis(request.getDiagnosis());
//        record.setPrescription(request.getPrescription());
//        record.setSymptoms(request.getSymptoms());
//        record.setNotes(request.getNotes());
//        record.setRecordDate(LocalDate.now());
//        record.setStatus(RecordStatus.ACTIVE);
//
//        MedicalRecord saved = medicalRecordRepository.save(record);
//        return toResponse(saved);
//    }

    @Override
    public List<MedicalRecordResponse> getAllRecords() {
        return medicalRecordRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordResponse getRecordById(Long id) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found!"));
        return toResponse(record);
    }

    @Override
    public List<MedicalRecordResponse> getRecordsByPatient(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalRecordResponse> getRecordsByDoctor(Long doctorId) {
        return medicalRecordRepository.findByDoctorId(doctorId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalRecordResponse> getRecordsByAppointment(Long appointmentId) {
        return medicalRecordRepository.findByAppointmentId(appointmentId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordResponse updateRecord(Long id, MedicalRecordRequest request) {
        MedicalRecord existing = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found!"));
        existing.setDiagnosis(request.getDiagnosis());
        existing.setPrescription(request.getPrescription());
        existing.setSymptoms(request.getSymptoms());
        existing.setNotes(request.getNotes());
        return toResponse(medicalRecordRepository.save(existing));
    }

    @Override
    public MedicalRecordResponse updateDischargeSummary(Long id, String dischargeSummary) {
        MedicalRecord existing = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found!"));
        existing.setDischargeSummary(dischargeSummary);
        return toResponse(medicalRecordRepository.save(existing));
    }

    @Override
    public MedicalRecordResponse updateStatus(Long id, RecordStatus status) {
        MedicalRecord existing = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found!"));
        existing.setStatus(status);
        return toResponse(medicalRecordRepository.save(existing));
    }

    @Override
    public void deleteRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }
}
