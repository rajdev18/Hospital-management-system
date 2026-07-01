package com.example.demo.appointment.controller;

import com.example.demo.appointment.dto.AppointmentRequest;
import com.example.demo.appointment.dto.AppointmentResponse;
import com.example.demo.appointment.entity.Appointment.AppointmentStatus;
import com.example.demo.appointment.service.AppointmentService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // book appointment
//    @PostMapping("/book")
//    public ResponseEntity<AppointmentResponse> bookAppointment(
//            @RequestBody AppointmentRequest request) {
//        return ResponseEntity.ok(appointmentService.bookAppointment(request));
//    }
    @PostMapping("/book")
    public ResponseEntity<AppointmentResponse> bookAppointment(
            @RequestBody AppointmentRequest request,
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(
                appointmentService.bookAppointment(request, token));
    }
    

    // get all appointments
    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // get appointment by id
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(
            @PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    // get appointments by patient
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponse>> getByPatient(
            @PathVariable Long patientId) {
        return ResponseEntity.ok(
            appointmentService.getAppointmentsByPatient(patientId));
    }
    
//    @PostMapping("/book")
//    public ResponseEntity<AppointmentResponse> bookAppointment(
//            @RequestBody AppointmentRequest request,
//            @RequestHeader("Authorization") String token) {
//        return ResponseEntity.ok(
//            appointmentService.bookAppointment(request, token));
//    }
    

    // get appointments by doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponse>> getByDoctor(
            @PathVariable Long doctorId) {
        return ResponseEntity.ok(
            appointmentService.getAppointmentsByDoctor(doctorId));
    }

    // update appointment status
    @PutMapping("/{id}/status")
    public ResponseEntity<AppointmentResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam AppointmentStatus status) {
        return ResponseEntity.ok(
            appointmentService.updateStatus(id, status));
    }

    // cancel appointment
    @PutMapping("/{id}/cancel")
    public ResponseEntity<AppointmentResponse> cancelAppointment(
            @PathVariable Long id) {
        return ResponseEntity.ok(
            appointmentService.cancelAppointment(id));
    }

    // delete appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok("Appointment deleted successfully!");
    }
}
