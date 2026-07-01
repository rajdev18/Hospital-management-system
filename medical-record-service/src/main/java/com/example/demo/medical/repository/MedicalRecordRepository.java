package com.example.demo.medical.repository;

import com.example.demo.medical.entity.MedicalRecord;
import com.example.demo.medical.entity.MedicalRecord.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    // get all records by patient
    List<MedicalRecord> findByPatientId(Long patientId);

    // get all records by doctor
    List<MedicalRecord> findByDoctorId(Long doctorId);

    // get record by appointment
    List<MedicalRecord> findByAppointmentId(Long appointmentId);

    // get records by patient and status
    List<MedicalRecord> findByPatientIdAndStatus(Long patientId, RecordStatus status);

    // get records by doctor and status
    List<MedicalRecord> findByDoctorIdAndStatus(Long doctorId, RecordStatus status);

    // check if record exists for appointment
    boolean existsByAppointmentId(Long appointmentId);
}
