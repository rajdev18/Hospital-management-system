package com.example.demo.medical.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

//@FeignClient(name = "appointment-service")
//public interface AppointmentClient {
//
//    @GetMapping("/api/appointments/{id}")
//    Object getAppointmentById(@PathVariable("id") Long id);
//}

@FeignClient(name = "appointment-service")
public interface AppointmentClient {
    @GetMapping("/api/appointments/{id}")
    Object getAppointmentById(
        @PathVariable("id") Long id,
        @RequestHeader("Authorization") String token
    );
}
