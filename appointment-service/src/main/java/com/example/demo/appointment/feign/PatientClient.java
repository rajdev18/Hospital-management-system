//package com.example.demo.appointment.feign;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(name = "patient-service")
//public interface PatientClient {
//
//    @GetMapping("/api/patients/{id}")
//    Object getPatientById(@PathVariable("id") Long id);
//}

package com.example.demo.appointment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "patient-service")
public interface PatientClient {

    @GetMapping("/api/patients/{id}")
    Object getPatientById(
        @PathVariable("id") Long id,
        @RequestHeader("Authorization") String token
    );
}

