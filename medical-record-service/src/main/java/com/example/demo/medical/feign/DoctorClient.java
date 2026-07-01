package com.example.demo.medical.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

//@FeignClient(name = "doctor-service")
//public interface DoctorClient {
//
//    @GetMapping("/api/doctors/{id}")
//    Object getDoctorById(@PathVariable("id") Long id);
//}


@FeignClient(name = "doctor-service")
public interface DoctorClient {
    @GetMapping("/api/doctors/{id}")
    Object getDoctorById(
        @PathVariable("id") Long id,
        @RequestHeader("Authorization") String token
    );
}
