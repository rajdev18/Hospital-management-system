package com.example.demo.gateway;

import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> patientRoute() {
        return GatewayRouterFunctions.route("patient-service")
                .route(RequestPredicates.path("/api/patients/**"),
                        HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> doctorRoute() {
        return GatewayRouterFunctions.route("doctor-service")
                .route(RequestPredicates.path("/api/doctors/**"),
                        HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8082"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> appointmentRoute() {
        return GatewayRouterFunctions.route("appointment-service")
                .route(RequestPredicates.path("/api/appointments/**"),
                        HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8083"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> medicalRecordRoute() {
        return GatewayRouterFunctions.route("medical-record-service")
                .route(RequestPredicates.path("/api/medical-records/**"),
                        HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8084"))
                .build();
    }
    
    
    
    @Bean
    public RouterFunction<ServerResponse> aiRoute() {
        return GatewayRouterFunctions.route("ai-service")
                .route(RequestPredicates.path("/ai/**"),
                        HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:5000"))
                .build();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}