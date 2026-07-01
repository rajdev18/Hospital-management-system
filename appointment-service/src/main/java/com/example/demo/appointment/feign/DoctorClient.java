//package com.example.demo.appointment.feign;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//
//@FeignClient(name="doctor-service")
//public interface DoctorClient {
//	 @GetMapping("/api/doctors/{id}")
//	    Object getDoctorById(@PathVariable("id") Long id);
//
//	    @GetMapping("/api/doctors/available")
//	    Object getAvailableDoctors();
//
//	    @PutMapping("/api/doctors/{id}/toggle-availability")
//	    Object toggleAvailability(@PathVariable("id") Long id);
//	}


package com.example.demo.appointment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "doctor-service")
public interface DoctorClient {

    @GetMapping("/api/doctors/{id}")
    Object getDoctorById(
        @PathVariable("id") Long id,
        @RequestHeader("Authorization") String token
    );

    @GetMapping("/api/doctors/available")
    Object getAvailableDoctors(
        @RequestHeader("Authorization") String token
    );

    @PutMapping("/api/doctors/{id}/toggle-availability")
    Object toggleAvailability(
        @PathVariable("id") Long id,
        @RequestHeader("Authorization") String token
    );
}
