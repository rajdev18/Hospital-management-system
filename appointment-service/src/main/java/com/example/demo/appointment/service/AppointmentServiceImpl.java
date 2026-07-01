package com.example.demo.appointment.service;

import com.example.demo.appointment.dto.AppointmentRequest;
import com.example.demo.appointment.dto.AppointmentResponse;
import com.example.demo.appointment.entity.Appointment;
import com.example.demo.appointment.entity.Appointment.AppointmentStatus;
import com.example.demo.appointment.feign.DoctorClient;
import com.example.demo.appointment.feign.PatientClient;
import com.example.demo.appointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorClient doctorClient;

    @Autowired
    private PatientClient patientClient;

    // convert entity to response
    private AppointmentResponse toResponse(Appointment a) {
        return new AppointmentResponse(
            a.getId(), a.getPatientId(), a.getDoctorId(),
            a.getAppointmentDate(), a.getAppointmentTime(),
            a.getSymptoms(), a.getStatus(), a.getNotes(),
            "Success"
        );
    }
    
    
    @Override
    public AppointmentResponse bookAppointment(
            AppointmentRequest request, String token) {

        // pass token to Feign calls
        Object patient = patientClient.getPatientById(
            request.getPatientId(), token);
        if (patient == null) {
            throw new RuntimeException("Patient not found!");
        }

        Object doctor = doctorClient.getDoctorById(
            request.getDoctorId(), token);
        if (doctor == null) {
            throw new RuntimeException("Doctor not found!");
        }

        // check slot
        boolean slotTaken = appointmentRepository
            .existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                request.getDoctorId(),
                request.getAppointmentDate(),
                request.getAppointmentTime()
            );
        if (slotTaken) {
            throw new RuntimeException("This slot is already booked!");
        }

        // create appointment
        Appointment appointment = new Appointment();
        appointment.setPatientId(request.getPatientId());
        appointment.setDoctorId(request.getDoctorId());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setSymptoms(request.getSymptoms());
        appointment.setNotes(request.getNotes());
        appointment.setStatus(AppointmentStatus.PENDING);

        Appointment saved = appointmentRepository.save(appointment);
        return toResponse(saved);
    }
    

//    @Override
//    public AppointmentResponse bookAppointment(AppointmentRequest request) {
//        // check if patient exists
//        Object patient = patientClient.getPatientById(request.getPatientId());
//        if (patient == null) {
//            throw new RuntimeException("Patient not found!");
//        }
//
//        // check if doctor exists
//        Object doctor = doctorClient.getDoctorById(request.getDoctorId());
//        if (doctor == null) {
//            throw new RuntimeException("Doctor not found!");
//        }
//
//        // check if slot already booked
//        boolean slotTaken = appointmentRepository
//            .existsByDoctorIdAndAppointmentDateAndAppointmentTime(
//                request.getDoctorId(),
//                request.getAppointmentDate(),
//                request.getAppointmentTime()
//            );
//        if (slotTaken) {
//            throw new RuntimeException("This slot is already booked!");
//        }
//
//        // create appointment
//        Appointment appointment = new Appointment();
//        appointment.setPatientId(request.getPatientId());
//        appointment.setDoctorId(request.getDoctorId());
//        appointment.setAppointmentDate(request.getAppointmentDate());
//        appointment.setAppointmentTime(request.getAppointmentTime());
//        appointment.setSymptoms(request.getSymptoms());
//        appointment.setNotes(request.getNotes());
//        appointment.setStatus(AppointmentStatus.PENDING);
//
//        Appointment saved = appointmentRepository.save(appointment);
//        return toResponse(saved);
//    }

    @Override
    public List<AppointmentResponse> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponse getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found!"));
        return toResponse(appointment);
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponse updateStatus(Long id, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found!"));
        appointment.setStatus(status);
        return toResponse(appointmentRepository.save(appointment));
    }

    @Override
    public AppointmentResponse cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found!"));
        appointment.setStatus(AppointmentStatus.CANCELLED);
        return toResponse(appointmentRepository.save(appointment));
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
