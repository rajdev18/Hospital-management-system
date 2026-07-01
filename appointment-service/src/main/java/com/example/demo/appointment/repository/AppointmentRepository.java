package com.example.demo.appointment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.appointment.entity.Appointment;
import com.example.demo.appointment.entity.Appointment.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	List<Appointment> findByPatientId(Long patientId);
    
    // get all appointments by doctor
    List<Appointment> findByDoctorId(Long doctorId);
    
    // get appointments by status
    List<Appointment> findByStatus(AppointmentStatus status);
    
    // get appointments by doctor and date
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);
    
    // get appointments by patient and status
    List<Appointment> findByPatientIdAndStatus(Long patientId, AppointmentStatus status);

    // check if appointment already exists
    boolean existsByDoctorIdAndAppointmentDateAndAppointmentTime(
        Long doctorId, LocalDate date, java.time.LocalTime time);
	
	
	
}
